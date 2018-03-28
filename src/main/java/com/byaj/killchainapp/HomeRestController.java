package com.byaj.killchainapp;

import com.byaj.killchainapp.models.Gamedata;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeRestController {

    @RequestMapping("/process2")
    public String dude(){
        Gamedata gd = new Gamedata("zyxc",false,false,"dude");
        return gd.toString();
    }
}
