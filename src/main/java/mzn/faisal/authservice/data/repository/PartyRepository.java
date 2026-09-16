package mzn.faisal.authservice.data.repository;

import mzn.faisal.authservice.data.db.entity.Party;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PartyRepository extends JpaRepository<Party, UUID> {

}
