package com.sherlock.apiservice.repository;

import com.sherlock.apiservice.model.Locker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LockerRepository extends JpaRepository<Locker, Integer> {
}
