package com.itSpace.site.repository;

import com.itSpace.site.model.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeesRepository extends JpaRepository<Employe,Integer > {
}
