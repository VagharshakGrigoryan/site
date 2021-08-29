package com.itSpace.site.security;

import com.itSpace.site.model.Employe;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class CurrentUser extends User {

    private final Employe employe;

    public CurrentUser(Employe employe) {
        super(employe.getEmail(), employe.getPassword(), AuthorityUtils.createAuthorityList(employe.getRole().name()));
        this.employe = employe;
    }

    public Employe getemploye() {
        return employe;
    }
}
