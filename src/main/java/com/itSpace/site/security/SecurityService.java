package com.itSpace.site.security;

import com.itSpace.site.model.Employe;
import com.itSpace.site.repository.EmployeesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SecurityService implements UserDetailsService {

    private final EmployeesRepository employeesRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Employe> byEmail = employeesRepository.findByEmail(s);
        if (byEmail.isEmpty()) {
            throw new UsernameNotFoundException("User with " + s + " user name does not exists");
        }

        return new CurrentUser(byEmail.get());
    }
}
