package Spring_MVC_with_Thymeleaf_Lab.LinkedOut.service;

import Spring_MVC_with_Thymeleaf_Lab.LinkedOut.db.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface EmployeeService {
    Employee findByFirstNameAndLastName(String firstName,String lastName);
    void save(Employee employee);
    List<Employee> findAllEmployee();
    Employee findById(UUID uuid);
    Employee findByFirstName(String firstName);

    boolean deleteCompanyById(UUID id);
}
