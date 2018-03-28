package com.byaj.killchainapp.models;

import org.aspectj.weaver.bcel.AtAjAttributes;

import java.util.*;

public class Target {
    private Long id;
    private String name;
    private TargetClass typetarget;
    private Boolean isActive;
    private String ipAddress;
    private Boolean isOnNetwork;
    private Long networkId;
    private String networkIp;
    private List<MitigationEnum> mitlist = new ArrayList<>();
    public Target() {
    }

    public Target(String name, TargetClass typetarget, Boolean isActive, String ipAddress, Boolean isOnNetwork, Long networkId, String networkIp, List<MitigationEnum> mitlist) {
        this.name = name;
        this.typetarget = typetarget;
        this.isActive = isActive;
        this.ipAddress = ipAddress;
        this.isOnNetwork = isOnNetwork;
        this.networkId = networkId;
        this.networkIp = networkIp;
        this.mitlist = mitlist;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public TargetClass getTypetarget() {
        return typetarget;
    }
    public void setTypetarget(TargetClass typetarget) {
        this.typetarget = typetarget;
    }
    public Boolean getActive() {
        return isActive;
    }
    public void setActive(Boolean active) {
        isActive = active;
    }
    public String getIpAddress() {
        return ipAddress;
    }
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
    public Boolean getOnNetwork() {
        return isOnNetwork;
    }
    public void setOnNetwork(Boolean onNetwork) {
        isOnNetwork = onNetwork;
    }

    public List<MitigationEnum> getMitlist() {
        return mitlist;
    }
    public void setMitlist(List<MitigationEnum> mitlist) {
        this.mitlist = mitlist;
    }

    public boolean processAttack(Attacks attack){
        Boolean doesSucceed = true;
        int blockcount = 0;

        // No full block to SQL Injection, so process partial blocks
        if (attack.equals(Attacks.SQLInjection)){
            if (mitlist.contains(MitigationEnum.WhiteListInputs)){
                blockcount += 1;
            }
            if (mitlist.contains(MitigationEnum.LeastPrivileges)){
                blockcount += 1;
            }
            if (mitlist.contains(MitigationEnum.EscapingUserInput)){
                blockcount += 1;
            }
            if (mitlist.contains(MitigationEnum.PreparedStatements)){
                blockcount += 1;
            }
            if (mitlist.contains(MitigationEnum.StoredProcedures)){
                blockcount += 1;
            }
            // No full block to LDAP Injection, so process partial blocks
        } else if (attack.equals(Attacks.LDAPInjection)){
            if (mitlist.contains(MitigationEnum.WhiteListInputs)){
                blockcount += 1;
            }
            if (mitlist.contains(MitigationEnum.LeastPrivileges)){
                blockcount += 1;
            }
            if (mitlist.contains(MitigationEnum.EscapingUserInput)){
                blockcount += 1;
            }
            // No full block to IMAP/SMTP Injection, so process partial blocks
        } else if (attack.equals(Attacks.IMAP_SMTPInjection)){
            if (mitlist.contains(MitigationEnum.WhiteListInputs)){
                blockcount += 1;
            }
            if (mitlist.contains(MitigationEnum.LeastPrivileges)){
                blockcount += 1;
            }
            if (mitlist.contains(MitigationEnum.EscapingUserInput)){
                blockcount += 1;
            }
            // First handle full blocks
        } else if (attack.equals(Attacks.XSS)){
            if (mitlist.contains(MitigationEnum.XSS_Prevention)){
                doesSucceed = false;
            }
            // Now process partial blocks
            if (mitlist.contains(MitigationEnum.WhiteListInputs)){
                blockcount += 1;
            }
            if (mitlist.contains(MitigationEnum.EscapingUserInput)){
                blockcount += 1;
            }
        } else if (attack.equals(Attacks.XSS)){
            if (mitlist.contains(MitigationEnum.XSS_Prevention)){
                doesSucceed = false;
            }
            // Now process partial blocks
            if (mitlist.contains(MitigationEnum.WhiteListInputs)){
                blockcount += 1;
            }
            if (mitlist.contains(MitigationEnum.EscapingUserInput)){
                blockcount += 1;
            }
        }

        double chance = Math.pow(.5, blockcount) * 100;

        // Randomly select attack from possible list
        Random r = new Random();
        int Low = 1;
        int Hi = 100;

        int Result = r.nextInt(Hi-Low) + Low;

        return Result < chance;
    }

    public boolean respondToAttack(Attacks attack)
    {
        // Create a list of possible mitigations that block the attack
        // and haven't already been done
        List<MitigationEnum> possmits = new ArrayList<>();
        if (attack == Attacks.SQLInjection)
        {
            if (typetarget.equals(TargetClass.DBServer)) {
                if (!mitlist.contains(MitigationEnum.LeastPrivileges)) {
                    possmits.add(MitigationEnum.LeastPrivileges);
                }
                if (!mitlist.contains(MitigationEnum.PreparedStatements)) {
                    possmits.add(MitigationEnum.PreparedStatements);
                }
                if (!mitlist.contains(MitigationEnum.StoredProcedures)) {
                    possmits.add(MitigationEnum.StoredProcedures);
                }
            } else if (typetarget.equals(TargetClass.WebServer)){
                if (!mitlist.contains(MitigationEnum.WhiteListInputs ))
                {
                    possmits.add(MitigationEnum.WhiteListInputs);
                }
                if (!mitlist.contains(MitigationEnum.EscapingUserInput))
                {
                    possmits.add(MitigationEnum.EscapingUserInput);
                }
            }
        } else if (attack.equals(Attacks.LDAPInjection)){
            if (typetarget.equals(TargetClass.DirectoryServer)) {
                if (!mitlist.contains(MitigationEnum.LeastPrivileges)) {
                    possmits.add(MitigationEnum.LeastPrivileges);
                }
            } else if (typetarget.equals(TargetClass.WebServer)){
                if (!mitlist.contains(MitigationEnum.WhiteListInputs ))
                {
                    possmits.add(MitigationEnum.WhiteListInputs);
                }
                if (!mitlist.contains(MitigationEnum.EscapingUserInput))
                {
                    possmits.add(MitigationEnum.EscapingUserInput);
                }
            }
        } else if (attack.equals(Attacks.IMAP_SMTPInjection)){
            if (typetarget.equals(TargetClass.EmailServer)) {
                if (!mitlist.contains(MitigationEnum.LeastPrivileges)) {
                    possmits.add(MitigationEnum.LeastPrivileges);
                }
            } else if (typetarget.equals(TargetClass.WebServer)){
                if (!mitlist.contains(MitigationEnum.EscapingUserInput))
                {
                    possmits.add(MitigationEnum.EscapingUserInput);
                }
                if (!mitlist.contains(MitigationEnum.WhiteListInputs ))
                {
                    possmits.add(MitigationEnum.WhiteListInputs);
                }
            }
        } else if (attack.equals(Attacks.XSS)){
                if (!mitlist.contains(MitigationEnum.WhiteListInputs ))
                {
                    possmits.add(MitigationEnum.WhiteListInputs);
                }
                if (!mitlist.contains(MitigationEnum.EscapingUserInput))
                {
                    possmits.add(MitigationEnum.EscapingUserInput);
                }
                if (!mitlist.contains(MitigationEnum.XSS_Prevention))
                {
                    possmits.add(MitigationEnum.XSS_Prevention);
                }
        } else if (attack.equals(Attacks.RogueMachine)){
            if (!mitlist.contains(MitigationEnum.PhysicalTraining ))
            {
                possmits.add(MitigationEnum.PhysicalTraining);
            }
            if (!mitlist.contains(MitigationEnum.PersonalTraining))
            {
                possmits.add(MitigationEnum.PersonalTraining);
            }
            if (!mitlist.contains(MitigationEnum.EmailTraining))
            {
                possmits.add(MitigationEnum.EmailTraining);
            }
        } else if (attack.equals(Attacks.CommandLine)){
            if (!mitlist.contains(MitigationEnum.Patches_Updates))
            {
                possmits.add(MitigationEnum.Patches_Updates);
            }
        }

        // Randomly select attack from possible list
        int lsize = possmits.size();
        Random r = new Random();
        int Low = 1;

        int Result = r.nextInt(lsize-Low) + Low;

        // if random unused attack exists add it to the mitigation list and return true
        if (lsize > 0){
            mitlist.add(possmits.get(Result - 1));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Target{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", typetarget=" + typetarget +
                ", isActive=" + isActive +
                ", ipAddress='" + ipAddress + '\'' +
                ", isOnNetwork=" + isOnNetwork +
                ", networkId='" + networkId + '\'' +
                ", mitlist=" + mitlist +
                '}';
    }

    public Long getNetworkId() {
        return networkId;
    }

    public void setNetworkId(Long networkId) {
        this.networkId = networkId;
    }

    public String getNetworkIp() {
        return networkIp;
    }

    public void setNetworkIp(String networkIp) {
        this.networkIp = networkIp;
    }
}
