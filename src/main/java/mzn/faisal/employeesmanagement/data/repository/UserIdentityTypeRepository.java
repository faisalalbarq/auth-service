package mzn.faisal.employeesmanagement.data.repository;

import mzn.faisal.employeesmanagement.data.db.entity.UserIdentityType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserIdentityTypeRepository extends JpaRepository<UserIdentityType, Integer> {
}
