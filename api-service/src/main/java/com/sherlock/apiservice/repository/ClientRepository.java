package com.sherlock.apiservice.repository;

import com.sherlock.apiservice.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
