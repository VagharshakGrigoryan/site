package com.itSpace.site.controllers;

import com.itSpace.site.model.Compani;
import com.itSpace.site.model.Employe;
import com.itSpace.site.repository.CompaniesRepository;
import com.itSpace.site.repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class EmpController {

    @Autowired
    private CompaniesRepository companiesRepository;
    @Autowired
    private EmployeesRepository employeesRepository;

    @GetMapping("/employe")
    public String EmpMain(ModelMap modelMap) {
        List<Employe> employes = employeesRepository.findAll();
        modelMap.addAttribute("employes", employes);
        return "employes-main";
    }

    @GetMapping("/employe/add")
    public String empAdd(ModelMap modelMap) {
        List<Compani> all = companiesRepository.findAll();
        modelMap.addAttribute("companis", all);
        return "employe-add";
    }


    @PostMapping("/employe/add")
    public String CompAdd(@ModelAttribute Employe employe ) {
        employeesRepository.save(employe);
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
                            @RequestParam String name, @RequestParam String surname,
                            @RequestParam String email, @RequestParam String phoneNumber,
                            @RequestParam double salary, @RequestParam String position,
                            ModelMap modelMap) {
        Employe employe = employeesRepository.findById(id).orElseThrow();
        employe.setName(name);
        employe.setSurname(surname);
        employe.setEmail(email);
        employe.setPhoneNumber(phoneNumber);
        employe.setSalary(salary);
        employe.setPosition(position);
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


