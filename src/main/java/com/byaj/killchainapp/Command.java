package com.byaj.killchainapp;

import com.byaj.killchainapp.models.Player;
import com.byaj.killchainapp.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class Command {
    @Autowired
    static PlayerRepository playerRepository;

    private static String acceptedOutsideCommands = "Commands recognized by killchain include: [[b;white;]rules], [[b;white;]man], [[b;white;]help], ping, scan, networkmap, patch, install, train, inject, do \n" +
            "To get more information about a command, type man <<command>> \n" +
            "For example, you could type [[b;white;]man rules].\n"+
            "If you need a list of all commands. Type [[b;white;]help].";
    private static String acceptedInsideCommands = "Commands recognized by killchain include: [[b;white;]rules], [[b;white;]man], [[b;white;]help], ping, scan, networkmap, patch, install, train, inject, do \n" +
            "To get more information about a command, type man <<command>> \n" +
            "For example, you could type [[b;white;]man rules].\n"+
            "If you need a list of all commands. Type [[b;white;]help].";
    public static String processOutside(String command){
        return outsideCommands(command, "outside");
    }

    public static String processInside(String command, Long userid){
        String temp = outsideCommands(command, "inside");
        if (temp.equals( "|||" )){
            temp = insideCommands(command, userid);
        }
        return temp;
    }

    public static String outsideCommands(String command, String where){
        // Commands that work when not logged in
        if (command.equals("help")) {
            if (where.equals("outside")){
                return acceptedOutsideCommands;
            } else {
                return acceptedInsideCommands;
            }
        } else if (command.length() > 3 && command.substring(0,4).equals("time")){
            return new Date().toString();
        } else if (command.length() > 3 && command.substring(0,4).equals("date")){
            return new Date().toString();
        } else if (command.length() > 4 && command.substring(0,4).equals("man ")){
            String infoRequest = command.substring(4);
            return manPage(infoRequest);
        } else if (command.length() > 4 && command.substring(0,5).equals("rules")){
            return rules();
        } else {
            if (where.equals("outside")) {
                if (command.length() > 5 && command.substring(0,6).equals("status")) {
                    return "You are not currently in-game...";
                } else {
                    return "Please enter a recognized command. If you need help, use the [[b;white;]man <<command>>]. \n" +
                            "For example, you could type [[b;white;]man rules].\n" +
                            "If you need a list of all commands. Type [[b;white;]help].";
                }
            } else {
                return "|||";
            }
        }
    }
    public static String insideCommands(String command, Long userid){

        // Commands that work when logged in
        if (command.equals("help")) {
            return acceptedInsideCommands;
        } else if (command.length() > 3 && command.substring(0,4).equals("time")){
            return new Date().toString();
        } else if (command.length() > 3 && command.substring(0,4).equals("date")){
            return new Date().toString();
        } else if (command.length() > 4 && command.substring(0,4).equals("man ")){
            String infoRequest = command.substring(4);
            return manPage(infoRequest);
        } else if (command.length() > 4 && command.substring(0,5).equals("rules")){
            return rules();
        } else if (command.length() > 5 && command.substring(0,6).equals("status")){
            return status(userid);
        } else {
            return "Please enter a recognized command. If you need help, use the [[b;white;]man <<command>>]. \n" +
                    "For example, you could type [[b;white;]man rules].\n"+
                    "If you need a list of all commands. Type [[b;white;]help].";
        }
    }

    private static String manPage(String command){
        if (command.equals("rules")) {
            return "Use the command [[b;white;]rules] to get the rules of Killchain";
        } else if (command.equals("help")) {
            return "Okay, that is a little redundant.";
        } else if (command.equals("time")) {
            return " The [[b;white;]time] command gives you the current network time.";
        } else if (command.equals("date")) {
            return " The [[b;white;]date] command gives you the current network date.";
        } else {
            return command + " is not a command that I can help you with.";
        }
    }

    private static String rules(){
        String myRules = "These are the rules of killchain: \n"+
                "1. Never talk about killchain. \n"+
                "2. Do damage onto others before others do damage onto you. \n";
        return myRules;
    }

    private static String status(Long userid){
        Player player = new Player();
        player = playerRepository.findOne(userid);
        String response = "";
        // name
        response += "Name: " + player.getName();
        // machine name
        // ip address
        // score
        // vms
        // protections

        return response;
    }
}

// instant: honeypot, bastion, incident response
// install:
// train: physical, email, personal
// inject: sql, ldap, imap
// do: tailgate, dropusb, phish
//