package com.itSpace.site.controllers;

import com.itSpace.site.model.Compani;
import com.itSpace.site.model.Employe;
import com.itSpace.site.model.Role;
import com.itSpace.site.model.Status;
import com.itSpace.site.repository.CompaniesRepository;
import com.itSpace.site.repository.EmployeesRepository;
import com.itSpace.site.security.CurrentUser;
import com.itSpace.site.service.CompanyService;
import com.itSpace.site.service.Empservice;
import com.itSpace.site.service.impl.EmplServImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class EmpController {

    private final CompaniesRepository companiesRepository;

    private final EmployeesRepository employeesRepository;
    private final Empservice empservice;
    private final CompanyService companyService;

    private final EmplServImpl emplServ;

    @GetMapping("/employe")
    public String EmpMain(ModelMap modelMap,CurrentUser currentUser) {
        List<Employe> employ = emplServ.findEmployeeByCompanyId(currentUser.getemploye().getCompani().getId());
            modelMap.addAttribute("employes", employ);
            return "employes-main";
        }

    @GetMapping("/employe/add")
    public String empAdd(ModelMap modelMap) {
        List<Compani> all = companiesRepository.findAll();
        modelMap.addAttribute("companis", all);
        return "employe-add";
    }


    @PostMapping("/employe/add")
    public String CompAdd(@ModelAttribute Employe employe,
                          @AuthenticationPrincipal CurrentUser currentUser) {
        employeesRepository.save(employe);
        Compani compani = companyService.getById(employe.getCompani().getId());
        empservice.CompAdd(employe, currentUser);
        companiesRepository.save(compani);
        return "redirect:/employe";
    }


    @GetMapping("/employe/{id}")
    public String EmpDetals(@PathVariable(value = "id") int id, ModelMap modelMap) {
        if (!employeesRepository.existsById(id)) {
            return "redirect:/employe";
        }
        Optional<Employe> employe = employeesRepository.findById(id);
        ArrayList<Employe> result = new ArrayList<>();
        employe.ifPresent(result::add);
        modelMap.addAttribute("empl", result);
        return "employe-detals";

    }

    @GetMapping("/employe/{id}/edit")
    public String EmpEdit(@PathVariable(value = "id") int id, ModelMap modelMap) {
        if (!employeesRepository.existsById(id)) {
            return "redirect:/employe";
        }
        List<Compani> all = companiesRepository.findAll();
        modelMap.addAttribute("companis", all);
        Optional<Employe> employe = employeesRepository.findById(id);
        ArrayList<Employe> result = new ArrayList<>();
        employe.ifPresent(result::add);
        modelMap.addAttribute("emp", result);
        return "employe-edit";
    }

    @PostMapping("/employe/{id}/edit")
    public String EmpUpdate(@PathVariable(value = "id") int id,
                            @RequestParam String email, @RequestParam String password,
                            @RequestParam String firstName, @RequestParam String lastName,
                            @RequestParam double salary, @RequestParam String position,
                            @RequestParam String phoneNumber, @RequestParam Role role,
                            @RequestParam Status status, ModelMap modelMap) {
        Employe employe = employeesRepository.findById(id).orElseThrow();
        employe.setEmail(email);
        employe.setPassword(password);
        employe.setFirstName(firstName);
        employe.setLastName(lastName);
        employe.setSalary(salary);
        employe.setPosition(position);
        employe.setPhoneNumber(phoneNumber);
        employe.setRole(role);
        employe.setStatus(status);
        employeesRepository.save(employe);
        return "redirect:/employe";
    }

    @PostMapping("/employe/{id}/remove")
    public String EmpDelet(@PathVariable(value = "id") int id, ModelMap modelMap) {
        Employe employe = employeesRepository.findById(id).orElseThrow();
        employeesRepository.delete(employe);
        return "redirect:/employe";
    }

}
