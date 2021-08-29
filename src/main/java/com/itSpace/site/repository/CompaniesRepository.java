package com.itSpace.site.repository;

import com.itSpace.site.model.Compani;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompaniesRepository extends JpaRepository<Compani,Integer> {
    Compani getById(int id);
}
