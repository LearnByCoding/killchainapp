package com.byaj.killchainapp.repositories;

import com.byaj.killchainapp.models.Vm;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VmRepository extends CrudRepository<Vm, Long> {
    List<Vm> findByOwner(Long owner);
}
