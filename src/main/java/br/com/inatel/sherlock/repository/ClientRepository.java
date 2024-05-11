package br.com.inatel.sherlock.repository;

import br.com.inatel.sherlock.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
