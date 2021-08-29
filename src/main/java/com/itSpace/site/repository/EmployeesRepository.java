package com.itSpace.site.repository;

import com.itSpace.site.model.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeesRepository extends JpaRepository<Employe,Integer> {
    Optional<Employe> findByEmail(String email);

    List<Employe> findEmployesByCompaniName(String valueOf);
    List<Employe> findEmployeeByCompaniId(int company);
}
