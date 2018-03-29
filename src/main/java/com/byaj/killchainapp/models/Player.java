package com.byaj.killchainapp.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String password;
    private String ipaddress;
    private String machinename;
    private Boolean isActive;
    private Boolean isDead;
    private String commands;
    private String targets;
    private String bitcoin;
    private String passwords;
    private String mitlist;


    private Long ttl;

    public Player() {
    }

    public Player(Long id, String name, String password, String ipaddress, String machinename, Boolean isActive, Boolean isDead, Long ttl) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.ipaddress = ipaddress;
        this.machinename = machinename;
        this.isActive = isActive;
        this.isDead = isDead;
        this.ttl = ttl;
    }

    public Player(String name, String password, String ipaddress, String machinename, Boolean isActive, Boolean isDead, String commands, String targets, String bitcoin, String passwords, String mitlist, Long ttl) {
        this.name = name;
        this.password = password;
        this.ipaddress = ipaddress;
        this.machinename = machinename;
        this.isActive = isActive;
        this.isDead = isDead;
        this.commands = commands;
        this.targets = targets;
        this.bitcoin = bitcoin;
        this.passwords = passwords;
        this.mitlist = mitlist;
        this.ttl = ttl;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public String getMachinename() {
        return machinename;
    }

    public void setMachinename(String machinename) {
        this.machinename = machinename;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getDead() {
        return isDead;
    }

    public void setDead(Boolean dead) {
        isDead = dead;
    }

    public Long getTtl() {
        return ttl;
    }

    public void setTtl(Long ttl) {
        this.ttl = ttl;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", ipaddress='" + ipaddress + '\'' +
                ", machinename='" + machinename + '\'' +
                ", isActive=" + isActive +
                ", isDead=" + isDead +
                ", ttl=" + ttl +
                '}';
    }

    public String getCommands() {
        return commands;
    }

    public void setCommands(String commands) {
        this.commands = commands;
    }

    public String getTargets() {
        return targets;
    }

    public void setTargets(String targets) {
        this.targets = targets;
    }

    public String getBitcoin() {
        return bitcoin;
    }

    public void setBitcoin(String bitcoin) {
        this.bitcoin = bitcoin;
    }

    public String getPasswords() {
        return passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }

    public String getMitlist() {
        return mitlist;
    }

    public void setMitlist(String mitlist) {
        this.mitlist = mitlist;
    }
}
