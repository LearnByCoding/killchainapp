package com.byaj.killchainapp;

import com.byaj.killchainapp.services.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {

    @Autowired
    CommandService commandService;

    @RequestMapping("/home")
    public String home(){
        return "home";
    }

    @RequestMapping("/")
    public String basic(Model model, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        Boolean hasCookie = false;
        for (Cookie cookie : cookies){
            if (cookie.getName().equals("killchain")){
                hasCookie = true;
            }
        }
        if (hasCookie) {
            return "index";
        } else {
            return "redirect:/home";
        }
    }

    @RequestMapping("/play/{game}")
    public String play(@PathVariable String game, Model model, HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        Boolean hasCookie = false;
        for (Cookie cookie : cookies){
            if (cookie.getName().equals("killchain")){
                hasCookie = true;
            }
        }

        Cookie cookie = new Cookie("killchain", game);
        cookie.setMaxAge(60 * 60);
        response.addCookie(cookie);

        return "index2";
    }

    @RequestMapping("/process")
    public String process(@RequestParam String gameid, Model model, HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        Boolean hasCookie = false;
        for (Cookie cookie : cookies){
            if (cookie.getName().equals("killchain")){
                hasCookie = true;
            }
        }

        Cookie cookie = new Cookie("killchain", gameid);
        cookie.setMaxAge(60 * 60);
        response.addCookie(cookie);

        return "redirect:/";
    }

    @PostMapping ("/outside")
    public @ResponseBody String processOutsideCommands(@RequestParam String command){
        command = commandService.processOutside(command);
        return command;
    }

    @PostMapping ("/inside")
    public @ResponseBody String processInsideCommands(@RequestParam String command){
        Long userid = 7L;
        command = commandService.processInside(command,  userid);
        return command;
    }
}
