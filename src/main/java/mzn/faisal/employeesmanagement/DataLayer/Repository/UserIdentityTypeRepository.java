package mzn.faisal.employeesmanagement.DataLayer.Repository;

import mzn.faisal.employeesmanagement.DataLayer.Entity.UserIdentityType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserIdentityTypeRepository extends JpaRepository<UserIdentityType, Integer> {
}
