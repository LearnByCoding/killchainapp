package com.byaj.killchainapp.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Corpnetwork {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String compname;
    private String ipnum;
    private Boolean isActive;
    private Boolean hasFirewall;
    private Long firewallLvl;
    private Boolean firewallActive;
    private Date firewallTTL;

    public Corpnetwork() {
    }

    public Corpnetwork(Boolean isDefault) {
        if (isDefault){
            id = 1L;
            compname="Urban Nerd Technologies";
            ipnum="38.8.83.2";
            isActive = true;
            hasFirewall = true;
            firewallLvl = 1L;
            firewallActive = true;
            firewallTTL = null;
        }
    }

    public Corpnetwork(Long id, String compname, String ipnum, Boolean isActive, Boolean hasFirewall, Long firewallLvl, Boolean firewallActive, Date firewallTTL) {
        this.id = id;
        this.compname = compname;
        this.ipnum = ipnum;
        this.isActive = isActive;
        this.hasFirewall = hasFirewall;
        this.firewallLvl = firewallLvl;
        this.firewallActive = firewallActive;
        this.firewallTTL = firewallTTL;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompname() {
        return compname;
    }

    public void setCompname(String compname) {
        this.compname = compname;
    }

    public String getIpnum() {
        return ipnum;
    }

    public void setIpnum(String ipnum) {
        this.ipnum = ipnum;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getHasFirewall() {
        return hasFirewall;
    }

    public void setHasFirewall(Boolean hasFirewall) {
        this.hasFirewall = hasFirewall;
    }

    public Long getFirewallLvl() {
        return firewallLvl;
    }

    public void setFirewallLvl(Long firewallLvl) {
        this.firewallLvl = firewallLvl;
    }

    public Boolean getFirewallActive() {
        return firewallActive;
    }

    public void setFirewallActive(Boolean firewallActive) {
        this.firewallActive = firewallActive;
    }

    public Date getFirewallTTL() {
        return firewallTTL;
    }

    public void setFirewallTTL(Date firewallTTL) {
        this.firewallTTL = firewallTTL;
    }


    @Override
    public String toString() {
        return "Corpnetwork{" +
                "id=" + id +
                ", compname='" + compname + '\'' +
                ", ipnum='" + ipnum + '\'' +
                ", isActive=" + isActive +
                ", hasFirewall=" + hasFirewall +
                ", firewallLvl=" + firewallLvl +
                ", firewallActive=" + firewallActive +
                ", firewallTTL=" + firewallTTL +
                '}';
    }
}
