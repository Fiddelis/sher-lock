package br.com.inatel.sherlock.repository;

import br.com.inatel.sherlock.models.Drawer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DrawerRepository extends JpaRepository<Drawer, Long> {
    List<Drawer> findDrawerByLockerId(Long lockerId);

}
