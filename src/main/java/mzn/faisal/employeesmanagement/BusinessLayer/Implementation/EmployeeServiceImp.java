//package mzn.faisal.employeesmanagement.BusinessLayer.Implementation;
//
//import mzn.faisal.employeesmanagement.BusinessLayer.Service.EmployeeService;
//import mzn.faisal.employeesmanagement.DataLayer.Entity.Employee;
//import mzn.faisal.employeesmanagement.DataLayer.Repository.EmployeeRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//@Service
//public class EmployeeServiceImp implements EmployeeService {
//
//    private final EmployeeRepository employeeRepository;
//
//    @Autowired
//    public EmployeeServiceImp(EmployeeRepository employeeRepository) {
//        this.employeeRepository = employeeRepository;
//    }
//
//
//    @Override
//    public List<Employee> findAll() {
//        return employeeRepository.findAll();
//    }
//
//    @Override
//    public Employee save(Employee employee) {
//        return employeeRepository.save(employee);
//    }
//
//    @Override
//    public void delete(Employee employee) {
//        employeeRepository.delete(employee);
//    }
//
//
//    @Override
//    public Employee findById(UUID theId) {
//        Optional<Employee> result = employeeRepository.findById(theId);
//        if (result.isPresent()) {
//            return result.get();
//        } else {
//            return null; //TODO: throw exception
//        }
//    }
//}
