package com.byaj.killchainapp.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Vm {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String Ipaddress;
    private Long owner;
    private String mitlist;
    private Boolean isActive;
    private String password;

    public Vm() {
    }

    public Vm(String ipaddress, Long owner, String mitlist, Boolean isActive, String password) {
        Ipaddress = ipaddress;
        this.owner = owner;
        this.mitlist = mitlist;
        this.isActive = isActive;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIpaddress() {
        return Ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        Ipaddress = ipaddress;
    }

    public Long getOwner() {
        return owner;
    }

    public void setOwner(Long owner) {
        this.owner = owner;
    }

    public String getMitlist() {
        return mitlist;
    }

    public void setMitlist(String mitlist) {
        this.mitlist = mitlist;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
