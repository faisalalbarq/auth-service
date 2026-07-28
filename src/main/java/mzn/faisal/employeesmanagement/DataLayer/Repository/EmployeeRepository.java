package mzn.faisal.employeesmanagement.DataLayer.Repository;

import mzn.faisal.employeesmanagement.DataLayer.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
}
