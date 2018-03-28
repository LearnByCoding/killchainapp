package com.byaj.killchainapp.models;

import org.aspectj.weaver.bcel.AtAjAttributes;

import javax.persistence.*;
import java.util.*;

@Entity
public class Target {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private TargetClass typetarget;
    private Boolean isActive;
    private String ipAddress;
    private Boolean isOnNetwork;
    private Long networkId;
    private String networkIp;
    private String mitlist;
    public Target() {
    }

    public Target(String name, TargetClass typetarget, Boolean isActive, String ipAddress, Boolean isOnNetwork, Long networkId, String networkIp, String mitlist) {
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


    public boolean processAttack(Attacks attack){
        Boolean doesSucceed = true;
        int blockcount = 0;

        // No full block to SQL Injection, so process partial blocks
        if (attack.equals(Attacks.SQLInjection)){
            if (mitlist.contains(MitigationEnum.WhiteListInputs.toString())){
                blockcount += 1;
            }
            if (mitlist.contains(MitigationEnum.LeastPrivileges.toString())){
                blockcount += 1;
            }
            if (mitlist.contains(MitigationEnum.EscapingUserInput.toString())){
                blockcount += 1;
            }
            if (mitlist.contains(MitigationEnum.PreparedStatements.toString())){
                blockcount += 1;
            }
            if (mitlist.contains(MitigationEnum.StoredProcedures.toString())){
                blockcount += 1;
            }
            // No full block to LDAP Injection, so process partial blocks
        } else if (attack.equals(Attacks.LDAPInjection)){
            if (mitlist.contains(MitigationEnum.WhiteListInputs.toString())){
                blockcount += 1;
            }
            if (mitlist.contains(MitigationEnum.LeastPrivileges.toString())){
                blockcount += 1;
            }
            if (mitlist.contains(MitigationEnum.EscapingUserInput.toString())){
                blockcount += 1;
            }
            // No full block to IMAP/SMTP Injection, so process partial blocks
        } else if (attack.equals(Attacks.IMAP_SMTPInjection)){
            if (mitlist.contains(MitigationEnum.WhiteListInputs.toString())){
                blockcount += 1;
            }
            if (mitlist.contains(MitigationEnum.LeastPrivileges.toString())){
                blockcount += 1;
            }
            if (mitlist.contains(MitigationEnum.EscapingUserInput.toString())){
                blockcount += 1;
            }
            // First handle full blocks
        } else if (attack.equals(Attacks.XSS)){
            if (mitlist.contains(MitigationEnum.XSS_Prevention.toString())){
                doesSucceed = false;
            }
            // Now process partial blocks
            if (mitlist.contains(MitigationEnum.WhiteListInputs.toString())){
                blockcount += 1;
            }
            if (mitlist.contains(MitigationEnum.EscapingUserInput.toString())){
                blockcount += 1;
            }
        } else if (attack.equals(Attacks.XSS)){
            if (mitlist.contains(MitigationEnum.XSS_Prevention.toString())){
                doesSucceed = false;
            }
            // Now process partial blocks
            if (mitlist.contains(MitigationEnum.WhiteListInputs.toString())){
                blockcount += 1;
            }
            if (mitlist.contains(MitigationEnum.EscapingUserInput.toString())){
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
        List<String> possmits = new ArrayList<>();
        if (attack == Attacks.SQLInjection)
        {
            if (typetarget.equals(TargetClass.DBServer)) {
                if (!mitlist.contains(MitigationEnum.LeastPrivileges.toString())) {
                    possmits.add(MitigationEnum.LeastPrivileges.toString());
                }
                if (!mitlist.contains(MitigationEnum.PreparedStatements.toString())) {
                    possmits.add(MitigationEnum.PreparedStatements.toString());
                }
                if (!mitlist.contains(MitigationEnum.StoredProcedures.toString())) {
                    possmits.add(MitigationEnum.StoredProcedures.toString());
                }
            } else if (typetarget.equals(TargetClass.WebServer)){
                if (!mitlist.contains(MitigationEnum.WhiteListInputs.toString() ))
                {
                    possmits.add(MitigationEnum.WhiteListInputs.toString());
                }
                if (!mitlist.contains(MitigationEnum.EscapingUserInput.toString()))
                {
                    possmits.add(MitigationEnum.EscapingUserInput.toString());
                }
            }
        } else if (attack.equals(Attacks.LDAPInjection)){
            if (typetarget.equals(TargetClass.DirectoryServer)) {
                if (!mitlist.contains(MitigationEnum.LeastPrivileges.toString())) {
                    possmits.add(MitigationEnum.LeastPrivileges.toString());
                }
            } else if (typetarget.equals(TargetClass.WebServer)){
                if (!mitlist.contains(MitigationEnum.WhiteListInputs.toString() ))
                {
                    possmits.add(MitigationEnum.WhiteListInputs.toString());
                }
                if (!mitlist.contains(MitigationEnum.EscapingUserInput.toString()))
                {
                    possmits.add(MitigationEnum.EscapingUserInput.toString());
                }
            }
        } else if (attack.equals(Attacks.IMAP_SMTPInjection)){
            if (typetarget.equals(TargetClass.EmailServer)) {
                if (!mitlist.contains(MitigationEnum.LeastPrivileges.toString())) {
                    possmits.add(MitigationEnum.LeastPrivileges.toString());
                }
            } else if (typetarget.equals(TargetClass.WebServer)){
                if (!mitlist.contains(MitigationEnum.EscapingUserInput.toString()))
                {
                    possmits.add(MitigationEnum.EscapingUserInput.toString());
                }
                if (!mitlist.contains(MitigationEnum.WhiteListInputs.toString() ))
                {
                    possmits.add(MitigationEnum.WhiteListInputs.toString());
                }
            }
        } else if (attack.equals(Attacks.XSS)){
                if (!mitlist.contains(MitigationEnum.WhiteListInputs.toString() ))
                {
                    possmits.add(MitigationEnum.WhiteListInputs.toString());
                }
                if (!mitlist.contains(MitigationEnum.EscapingUserInput.toString()))
                {
                    possmits.add(MitigationEnum.EscapingUserInput.toString());
                }
                if (!mitlist.contains(MitigationEnum.XSS_Prevention.toString()))
                {
                    possmits.add(MitigationEnum.XSS_Prevention.toString());
                }
        } else if (attack.equals(Attacks.RogueMachine)){
            if (!mitlist.contains(MitigationEnum.PhysicalTraining.toString() ))
            {
                possmits.add(MitigationEnum.PhysicalTraining.toString());
            }
            if (!mitlist.contains(MitigationEnum.PersonalTraining.toString()))
            {
                possmits.add(MitigationEnum.PersonalTraining.toString());
            }
            if (!mitlist.contains(MitigationEnum.EmailTraining.toString()))
            {
                possmits.add(MitigationEnum.EmailTraining.toString());
            }
        } else if (attack.equals(Attacks.CommandLine)){
            if (!mitlist.contains(MitigationEnum.Patches_Updates.toString()))
            {
                possmits.add(MitigationEnum.Patches_Updates.toString());
            }
        }

        // Randomly select attack from possible list
        int lsize = possmits.size();
        Random r = new Random();
        int Low = 1;

        int Result = r.nextInt(lsize-Low) + Low;

        // if random unused attack exists add it to the mitigation list and return true
        if (lsize > 0){
            mitlist += possmits.get(Result - 1);
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


    public String getMitlist() {
        return mitlist;
    }

    public void setMitlist(String mitlist) {
        this.mitlist = mitlist;
    }
}
