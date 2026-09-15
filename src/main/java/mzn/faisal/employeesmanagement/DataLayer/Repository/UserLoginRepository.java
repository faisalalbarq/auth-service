package mzn.faisal.employeesmanagement.DataLayer.Repository;

import mzn.faisal.employeesmanagement.DataLayer.db.Entity.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserLoginRepository extends JpaRepository<UserLogin, UUID> {
}
