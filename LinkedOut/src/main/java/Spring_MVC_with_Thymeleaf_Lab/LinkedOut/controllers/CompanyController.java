package Spring_MVC_with_Thymeleaf_Lab.LinkedOut.controllers;

import Spring_MVC_with_Thymeleaf_Lab.LinkedOut.db.entity.Company;
import Spring_MVC_with_Thymeleaf_Lab.LinkedOut.dto.CompanyDTO;
import Spring_MVC_with_Thymeleaf_Lab.LinkedOut.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @GetMapping("/add")
    public String companiesAdd(Model model) {
        model.addAttribute("companyDTO", new CompanyDTO());
        return "company-add";
    }

    @PostMapping("/add")
    public String companiesAdd(@Valid @ModelAttribute("companyDTO") CompanyDTO companyDTO,
                               BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "company-add";
        }

        Company company = Company.builder()
                .name(companyDTO.getName())
                .town(companyDTO.getTown())
                .description(companyDTO.getDescription())
                .budget(companyDTO.getBudget()).build();

        this.companyService.saveCompany(company);

        return "redirect:/";
    }

    @GetMapping("/all")
    public String companiesAll(Model model) {
        List<Company> companyList = companyService.findAll();
        model.addAttribute("companyList", companyList);
        return "company-all";
    }

    @GetMapping("/details/{id}")
    public String companiesDetails(@PathVariable UUID id, Model model) {
        Company company = companyService.findById(id);
        if (company != null) {
            model.addAttribute("company", company);
        } else {
            model.addAttribute("error", "Company not found");
        }

        return "company-details";
    }

    @PostMapping("/delete")
    public String deleteCompany(@RequestParam("id") UUID id, RedirectAttributes redirectAttributes) {
        if (companyService.deleteCompanyById(id)) {
            redirectAttributes.addFlashAttribute("message", "Company deleted successfully.");
        } else {
            redirectAttributes.addFlashAttribute("error", "Company deletion failed.");
        }
        return "redirect:/companies/all";
    }
}
