package com.itSpace.site.util;

import com.itSpace.site.model.Compani;
import com.itSpace.site.model.Employe;
import com.itSpace.site.model.Role;
import com.itSpace.site.repository.CompaniesRepository;
import com.itSpace.site.repository.EmployeesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OnApplicationStartEvent implements ApplicationListener<ApplicationReadyEvent> {


    private final EmployeesRepository employeesRepository;
    private final CompaniesRepository companiesRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        if (employeesRepository.findByEmail("admin@mail.com").isEmpty()) {
            Compani company = companiesRepository.save(Compani.builder()
                    .name("Itspace")
                    .address("Gyumri")
                    .size(11)
                    .build());
            employeesRepository.save(Employe.builder()
                    .email("admin@mail.com")
                    .password(passwordEncoder.encode("admin"))
                    .lastName("admin")
                    .firstName("admin")
                    .salary(1000)
                    .phoneNumber("+3747777777")
                    .position("Manager")
                    .compani(company)
                    .role(Role.ADMIN)
                    .build());
        }

    }

}