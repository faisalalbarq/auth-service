package mzn.faisal.authservice.data.repository;

import mzn.faisal.authservice.data.db.entity.UserIdentityType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserIdentityTypeRepository extends JpaRepository<UserIdentityType, Integer> {
}
