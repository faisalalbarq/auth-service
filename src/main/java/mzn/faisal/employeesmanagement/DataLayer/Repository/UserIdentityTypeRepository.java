package mzn.faisal.employeesmanagement.DataLayer.Repository;

import mzn.faisal.employeesmanagement.DataLayer.db.Entity.UserIdentityType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserIdentityTypeRepository extends JpaRepository<UserIdentityType, Integer> {
}
