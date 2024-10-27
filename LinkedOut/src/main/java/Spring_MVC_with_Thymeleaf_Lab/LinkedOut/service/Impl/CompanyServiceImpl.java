package Spring_MVC_with_Thymeleaf_Lab.LinkedOut.service.Impl;

import Spring_MVC_with_Thymeleaf_Lab.LinkedOut.db.entity.Company;
import Spring_MVC_with_Thymeleaf_Lab.LinkedOut.db.repository.CompanyRepository;
import Spring_MVC_with_Thymeleaf_Lab.LinkedOut.service.CompanyService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Override
    public void saveCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public Company findById(UUID id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public boolean findByName(String name) {
        return companyRepository.findByName(name).isEmpty();
    }

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    @Transactional
    public boolean deleteCompanyById(UUID id) {
        if (companyRepository.findById(id).isEmpty()) {
            return false;
        }

        companyRepository.deleteById(id);
        return true;
    }
}
