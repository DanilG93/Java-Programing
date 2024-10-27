package Spring_MVC_with_Thymeleaf_Lab.LinkedOut.service.Impl;

import Spring_MVC_with_Thymeleaf_Lab.LinkedOut.db.entity.Employee;
import Spring_MVC_with_Thymeleaf_Lab.LinkedOut.db.repository.EmployeeRepository;
import Spring_MVC_with_Thymeleaf_Lab.LinkedOut.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Employee findByFirstNameAndLastName(String firstName, String lastName) {
        return employeeRepository.findDistinctByLastNameAndFirstName(lastName,firstName);
    }

    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(UUID uuid) {
        return employeeRepository.findById(uuid).orElse(null);
    }

    @Override
    public Employee findByFirstName(String firstName) {
        return employeeRepository.findByFirstName(firstName);
    }

    @Override
    public boolean deleteCompanyById(UUID id) {
        if (employeeRepository.findById(id).isEmpty()) {
            return false;
        }
        employeeRepository.deleteById(id);
        return true;
    }
}
