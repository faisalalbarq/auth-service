package mzn.faisal.employeesmanagement.DataLayer.Repository;

import mzn.faisal.employeesmanagement.DataLayer.Entity.Party;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PartyRepository extends JpaRepository<Party, UUID> {

}
