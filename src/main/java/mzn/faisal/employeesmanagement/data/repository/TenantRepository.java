package mzn.faisal.employeesmanagement.data.repository;

import mzn.faisal.employeesmanagement.data.db.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TenantRepository extends JpaRepository<Tenant, UUID> {
    Optional<Tenant> findByPartyId(UUID partyId);
}
