package com.itSpace.site.service;

import com.itSpace.site.model.Employe;
import com.itSpace.site.security.CurrentUser;
import org.springframework.stereotype.Service;


@Service
public interface Empservice {

    void CompAdd(Employe employe, CurrentUser currentUser);
}

