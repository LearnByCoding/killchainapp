package com.byaj.killchainapp;

import com.byaj.killchainapp.models.Corpnetwork;
import com.byaj.killchainapp.models.Player;
import com.byaj.killchainapp.models.Target;
import com.byaj.killchainapp.models.TargetClass;
import com.byaj.killchainapp.repositories.CorpnetworkRepository;
import com.byaj.killchainapp.repositories.PlayerRepository;
import com.byaj.killchainapp.repositories.TargetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader  implements CommandLineRunner{
    @Autowired
    CorpnetworkRepository corpnetworkRepository;

    @Autowired
    TargetRepository targetRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Override
    public void run(String... strings) throws Exception {
        Corpnetwork corpnetwork = new Corpnetwork(true);
        corpnetworkRepository.save(corpnetwork);

        Target target = new Target("Web Server", TargetClass.WebServer, true, "9.9.9.2", true, 1L, "9.9.9.1", null);
        targetRepository.save(target);

        target = new Target("DB Server", TargetClass.DBServer, true, "9.9.9.3", true, 1L, "9.9.9.1", null);
        targetRepository.save(target);

        target = new Target("Directory Server", TargetClass.DirectoryServer, true, "9.9.9.4", true, 1L, "9.9.9.1", null);
        targetRepository.save(target);

        target = new Target("Email Server", TargetClass.EmailServer, true, "9.9.9.5", true, 1L, "9.9.9.1", null);
        targetRepository.save(target);

        target = new Target("File Server", TargetClass.FileServer, true, "9.9.9.6", true, 1L, "9.9.9.1", null);
        targetRepository.save(target);

        Player player = new Player(
                "ajius",
                "lincoln1",
                "1.2.3.4",
                "maydeceiver",
                true,
                false,
                "",
                "",
                "100",
                "",
                "",
                100L);
        playerRepository.save(player);
    }
}
