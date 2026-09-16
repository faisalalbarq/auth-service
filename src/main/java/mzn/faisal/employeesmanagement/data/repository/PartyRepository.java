package mzn.faisal.employeesmanagement.data.repository;

import mzn.faisal.employeesmanagement.data.db.entity.Party;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PartyRepository extends JpaRepository<Party, UUID> {

}
