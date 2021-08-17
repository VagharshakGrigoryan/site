package com.itSpace.site.controllers;
import com.itSpace.site.model.Employe;
import com.itSpace.site.repository.EmployeesRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class EmpController {
    private final EmployeesRepository employeesRepository;


    public EmpController(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    @GetMapping("/employe")
    public String EmpMain(Model model) {
        Iterable<Employe> employes = employeesRepository.findAll();
        model.addAttribute("employes", employes);
        return "employes-main";
    }

    @GetMapping("/employe/add")
    public String empAdd(Model model) {
        return "employe-add";
    }


    @PostMapping("/employe/add")
    public String CompPostAdd(@RequestParam String name,
                              @RequestParam String surname,
                              @RequestParam String email,
                              @RequestParam String phoneNumber,
                              @RequestParam double salary,
                              @RequestParam String position,
                              @RequestParam String company_id,

                              Model model) {
        Employe employe =
                new Employe(name,surname,email,phoneNumber,salary,position,company_id);
        employeesRepository.save(employe);
        return "redirect:/employe";
    }

    @GetMapping("/employe/{id}")
    public String EmpDetals(@PathVariable(value = "id") long id, Model model) {
        if (!employeesRepository.existsById(id)) {
            return "redirect:/employe";
        }
        Optional<Employe> employe = employeesRepository.findById(id);
        ArrayList<Employe> result = new ArrayList<>();
        employe.ifPresent(result::add);
        model.addAttribute("empl", result);
        return "employe-detals";

    }

    @GetMapping("/employe/{id}/edit")
    public String EmpEdit(@PathVariable(value = "id") long id, Model model) {
        if (!employeesRepository.existsById(id)) {
            return "redirect:/employe";
        }
        Optional<Employe> employe = employeesRepository.findById(id);
        ArrayList<Employe> result = new ArrayList<>();
        employe.ifPresent(result::add);
        model.addAttribute("emp", result);
        return "employe-edit";
    }

    @PostMapping("/employe/{id}/edit")
    public String EmpUpdate(@PathVariable(value = "id") long id,
                            @RequestParam String name, @RequestParam String surname,
                            @RequestParam String email, @RequestParam String phoneNumber,
                            @RequestParam double salary, @RequestParam String position,
                            @RequestParam String company_id,
                            Model model) {
        Employe employe = employeesRepository.findById(id).orElseThrow();
        employe.setName(name);
        employe.setSurname(surname);
        employe.setEmail(email);
        employe.setPhoneNumber(phoneNumber);
        employe.setSalary(salary);
        employe.setPosition(position);
        employe.setCompany_id(company_id);
        employeesRepository.save(employe);
        return "redirect:/employe";
    }

    @PostMapping("/employe/{id}/remove")
    public String EmpDelet(@PathVariable(value = "id") long id, Model model) {
        Employe employe = employeesRepository.findById(id).orElseThrow();
        employeesRepository.delete(employe);
        return "redirect:/employe";
    }
}


