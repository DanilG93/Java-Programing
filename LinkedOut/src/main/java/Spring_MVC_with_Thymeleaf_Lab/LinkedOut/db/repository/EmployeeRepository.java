package Spring_MVC_with_Thymeleaf_Lab.LinkedOut.db.repository;

import Spring_MVC_with_Thymeleaf_Lab.LinkedOut.db.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    Employee findDistinctByLastNameAndFirstName(String firstName,String lastName);
}
