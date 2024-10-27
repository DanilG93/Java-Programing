package Spring_MVC_with_Thymeleaf_Lab.LinkedOut.service;

import Spring_MVC_with_Thymeleaf_Lab.LinkedOut.db.entity.Company;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface CompanyService {
    void saveCompany(Company company);
    Company findById(UUID id);
    boolean findByName(String name);
    List<Company> findAll();
    boolean deleteCompanyById(UUID id);
}
