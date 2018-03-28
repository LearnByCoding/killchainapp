package com.byaj.killchainapp.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Gamedata {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String gameid;
    private Boolean isBegun;
    private Boolean isDone;
    private String log;

    public Gamedata() {
    }

    public Gamedata(String gameid, Boolean isBegun, Boolean isDone, String log) {
        this.gameid = gameid;
        this.isBegun = isBegun;
        this.isDone = isDone;
        this.log = log;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGameid() {
        return gameid;
    }

    public void setGameid(String gameid) {
        this.gameid = gameid;
    }

    public Boolean getBegun() {
        return isBegun;
    }

    public void setBegun(Boolean begun) {
        isBegun = begun;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    @Override
    public String toString() {
        return "Gamedata{" +
                "id=" + id +
                ", gameid='" + gameid + '\'' +
                ", isBegun=" + isBegun +
                ", isDone=" + isDone +
                ", log='" + log + '\'' +
                '}';
    }
}
