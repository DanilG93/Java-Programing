package Spring_MVC_with_Thymeleaf_Lab.LinkedOut.controllers;

import Spring_MVC_with_Thymeleaf_Lab.LinkedOut.db.entity.Employee;
import Spring_MVC_with_Thymeleaf_Lab.LinkedOut.dto.EmployeeDTO;
import Spring_MVC_with_Thymeleaf_Lab.LinkedOut.service.CompanyService;
import Spring_MVC_with_Thymeleaf_Lab.LinkedOut.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    CompanyService companyService;
    @Autowired
    EmployeeService employeeService;


    @GetMapping("/add")
    public String employeesAdd(Model model) {
        model.addAttribute("employeeDTO", new EmployeeDTO());
        model.addAttribute("companies", companyService.findAll());
        return "employee-add.html";
    }

    @PostMapping("/add")
    public String employeesAdd(@Valid @ModelAttribute("employeeDTO") EmployeeDTO employeeDTO,
                               BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "employee-add";
        }

        Employee byFirstNameAndLastName = employeeService.findByFirstNameAndLastName(employeeDTO.getFirstName(), employeeDTO.getLastName());

        if (byFirstNameAndLastName != null) {
            ObjectError error = new ObjectError("employee","Employee already exists.");
            bindingResult.addError(error);
            return "employee-add";
        }

        Employee employee = Employee.builder()
                .firstName(employeeDTO.getFirstName())
                .lastName(employeeDTO.getLastName())
                .educationLevel(employeeDTO.getEducationLevel())
                .company(employeeDTO.getCompany())
                .jobTitle(employeeDTO.getJobTitle())
                .birthDate(employeeDTO.getBirthDate())
                .salary(employeeDTO.getSalary()).build();
        employeeService.save(employee);

        return "redirect:/";
    }

    @GetMapping("/all")
    public String employeesAll(Model model) {
        List<Employee> employeeList = employeeService.findAllEmployee();
        model.addAttribute("employeeList", employeeList);
        return "employee-all";
    }

    @GetMapping("/details/{id}")
    public String employeesDetails(@PathVariable UUID id,Model model) {
        Employee employee = employeeService.findById(id);
        if (employee != null) {
            model.addAttribute("employee", employee);
        } else {
            model.addAttribute("error", "Employee not found");
        }

        return "employee-details";
    }

    @PostMapping("/delete")
    public String deleteEmployee(@RequestParam("id") UUID id, RedirectAttributes redirectAttributes) {
        if (employeeService.deleteCompanyById(id)) {
            redirectAttributes.addFlashAttribute("message", "Employee deleted successfully.");
        } else {
            redirectAttributes.addFlashAttribute("error", "Employee deletion failed.");
        }
        return "redirect:/employees/all";
    }
}
