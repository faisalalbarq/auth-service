package mzn.faisal.employeesmanagement.BusinessLayer.Service;

import mzn.faisal.employeesmanagement.DataLayer.Entity.Employee;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {
    List<Employee> findAll();
    Employee save(Employee employee);
    void delete(Employee employee);
    Employee findById(UUID id);
}
