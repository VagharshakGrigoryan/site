package com.itSpace.site.service.impl;

import com.itSpace.site.model.Employe;
import com.itSpace.site.repository.EmployeesRepository;
import com.itSpace.site.security.CurrentUser;
import com.itSpace.site.service.Empservice;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class EmplServImpl implements Empservice {

    private final EmployeesRepository employeesRepository;
    private final PasswordEncoder passwordEncoder;


    public List<Employe> findEmployeeByCompanyId(int companyId) {
        return employeesRepository.findEmployeeByCompaniId(companyId);
    }
    public List<Employe> findAll() {
        return employeesRepository.findAll();
    }


    @Override
    public void CompAdd(Employe employe, CurrentUser currentUser) {
        employe.setPassword(passwordEncoder.encode(employe.getPassword()));
        employeesRepository.save(employe);



    }


    }

