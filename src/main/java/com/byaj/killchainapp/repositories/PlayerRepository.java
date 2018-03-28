package com.byaj.killchainapp.repositories;

import com.byaj.killchainapp.models.Player;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Long> {
    Player findByNameAndPassword(String name, String password);
}
