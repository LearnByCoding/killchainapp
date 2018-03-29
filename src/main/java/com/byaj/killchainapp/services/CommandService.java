package com.byaj.killchainapp.services;

import com.byaj.killchainapp.models.Player;

import java.util.Date;

public interface CommandService {
    public String processOutside(String command);
    public String processInside(String command, Long userid);
    public String outsideCommands(String command, String where);
    public String insideCommands(String command, Long userid);
}
