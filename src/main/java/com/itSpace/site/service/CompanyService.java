package com.itSpace.site.service;


import com.itSpace.site.model.Compani;
import com.itSpace.site.repository.CompaniesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompaniesRepository companyRepository;


    public Compani getById(int id) {
        return companyRepository.getById(id);
    }


}

