package com.itSpace.site.repository;

import com.itSpace.site.model.Employe;
import org.springframework.data.repository.CrudRepository;

public interface EmployeesRepository extends CrudRepository <Employe,Long > {
}
