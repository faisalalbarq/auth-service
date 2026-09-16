package mzn.faisal.employeesmanagement.data.repository;

import mzn.faisal.employeesmanagement.data.db.entity.UserIdentity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserIdentityRepository extends JpaRepository<UserIdentity, UUID> {
    boolean existsByUserIdentityValue(String userIdentityValue);
    Optional<UserIdentity> findByUserIdentityValue(String userIdentityValue);
}
