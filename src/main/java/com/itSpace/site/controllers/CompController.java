package com.itSpace.site.controllers;

import com.itSpace.site.model.Compani;
import com.itSpace.site.repository.CompaniesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CompController {

    private final CompaniesRepository companiesRepository;

    @GetMapping("/company")
    public String SiteMain(ModelMap modelMap) {
        List<Compani> companis = companiesRepository.findAll();
        modelMap.addAttribute("companis", companis);
        return "company-main";
    }

    @GetMapping("/company/add")
    public String compAdd() {
        return "company-add";
    }


    @PostMapping("/company/add")
    public String CompPostAdd(@ModelAttribute Compani compani) {
        companiesRepository.save(compani);
        return "redirect:/company";
    }

    @GetMapping("/company/{id}")
    public String CompDetals(@PathVariable(value = "id") int id, Model model) {
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
    public String CompEdit(@PathVariable(value = "id") int id, ModelMap modelMap) {
        if (!companiesRepository.existsById(id)) {
            return "redirect:/company";
        }
        Optional<Compani> comp = companiesRepository.findById(id);
        ArrayList<Compani> result = new ArrayList<>();
        comp.ifPresent(result::add);
        modelMap.addAttribute("comp", result);
        return "company-edit";
    }

    @PostMapping("/company/{id}/edit")
    public String CompUpdate(@PathVariable(value = "id") int id,
                             @RequestParam String name, @RequestParam int size,
                             @RequestParam String address, ModelMap modelMap) {
        Compani compani = companiesRepository.findById(id).orElseThrow();
        compani.setName(name);
        compani.setSize(size);
        compani.setAddress(address);
        companiesRepository.save(compani);
        return "redirect:/company";
    }

    @PostMapping("/company/{id}/remove")
    public String CompDelet(@PathVariable(value = "id") int id, ModelMap modelMap) {
        Compani compani = companiesRepository.findById(id).orElseThrow();
        companiesRepository.delete(compani);
        return "redirect:/company";
    }
}


