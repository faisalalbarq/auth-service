package mzn.faisal.authservice.data.repository;

import mzn.faisal.authservice.data.db.entity.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserLoginRepository extends JpaRepository<UserLogin, UUID> {
}
