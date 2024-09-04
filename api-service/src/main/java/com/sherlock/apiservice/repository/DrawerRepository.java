package com.sherlock.apiservice.repository;

import com.sherlock.apiservice.model.Drawer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DrawerRepository extends JpaRepository<Drawer, Integer> {
    List<Drawer> findAllByLockerId(Integer id);
}
