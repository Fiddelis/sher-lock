package br.com.inatel.sherlock.repository;

import br.com.inatel.sherlock.models.Locker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LockerRepository extends JpaRepository<Locker, Long> {
}
