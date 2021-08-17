package com.itSpace.site.controllers;

import com.itSpace.site.model.Compani;
import com.itSpace.site.repository.CompaniesRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class CompController {

    private final CompaniesRepository companiesRepository;

    @GetMapping("/company")
    public String SiteMain(Model model) {
        Iterable<Compani> companis = companiesRepository.findAll();
        model.addAttribute("companis", companis);
        return "company-main";
    }
    @GetMapping("/company/add")
    public String compAdd(Model model) {
        return "company-add";
    }

    public CompController(CompaniesRepository companiesRepository) {
        this.companiesRepository = companiesRepository;
    }

    @PostMapping("/company/add")
    public String CompPostAdd(@RequestParam String name,
                              @RequestParam int size,
                              @RequestParam String address,
                              Model model) {
        Compani compani =
                new Compani(name,size,address);
        companiesRepository.save(compani);
        return "redirect:/company";
    }

    @GetMapping("/company/{id}")
    public String CompDetals(@PathVariable(value = "id") long id, Model model) {
        if (!companiesRepository.existsById(id)) {
            return "redirect:/company";
        }
        Optional<Compani> compani = companiesRepository.findById(id);
        ArrayList<Compani> result = new ArrayList<>();
        compani.ifPresent(result::add);
        model.addAttribute("company", result);
        return "company-detals";

    }
    @GetMapping("/company/{id}/edit")
    public String CompEdit(@PathVariable(value = "id") long id, Model model) {
        if (!companiesRepository.existsById(id)) {
            return "redirect:/company";
        }
        Optional<Compani> comp = companiesRepository.findById(id);
        ArrayList<Compani> result = new ArrayList<>();
        comp.ifPresent(result::add);
        model.addAttribute("comp", result);
        return "company-edit";
    }

    @PostMapping("/company/{id}/edit")
    public String CompUpdate(@PathVariable(value = "id") long id,
                                 @RequestParam String name, @RequestParam int size,
                                 @RequestParam String address, Model model) {
        Compani compani = companiesRepository.findById(id).orElseThrow();
        compani.setName(name);
        compani.setSize(size);
        compani.setAddress(address);
        companiesRepository.save(compani);
        return "redirect:/company";
    }

    @PostMapping("/company/{id}/remove")
    public String CompDelet(@PathVariable(value = "id") long id, Model model) {
        Compani compani = companiesRepository.findById(id).orElseThrow();
        companiesRepository.delete(compani);
        return "redirect:/company";
    }
}


