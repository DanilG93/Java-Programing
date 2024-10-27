package Spring_MVC_with_Thymeleaf_Lab.LinkedOut.util;

import Spring_MVC_with_Thymeleaf_Lab.LinkedOut.db.entity.Company;
import Spring_MVC_with_Thymeleaf_Lab.LinkedOut.service.CompanyService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueNameValidator implements ConstraintValidator<UniqueName,String> {

    @Autowired
    CompanyService companyService;

    @Override
    public void initialize(UniqueName constraintAnnotation) {

    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        if (name == null || name.isEmpty()) {
            return true;
        }
        return companyService.findByName(name);
    }
}
