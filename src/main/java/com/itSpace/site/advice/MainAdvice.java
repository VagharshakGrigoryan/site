package com.itSpace.site.advice;

import com.itSpace.site.model.Employe;
import com.itSpace.site.security.CurrentUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class MainAdvice {


    @ModelAttribute("currentUser")
    public Employe currentUser(@AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser == null) {
            return new Employe();
        }
        return currentUser.getemploye();
    }

}
