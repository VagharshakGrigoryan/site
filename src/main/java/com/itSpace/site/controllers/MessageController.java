package com.itSpace.site.controllers;


import com.itSpace.site.model.Employe;
import com.itSpace.site.model.Message;
import com.itSpace.site.security.CurrentUser;
import com.itSpace.site.service.MessageService;
import com.itSpace.site.service.impl.EmplServImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MessageController {


    private final EmplServImpl employeeService;
    private final MessageService messageService;

    @GetMapping("/sendMessage")
    public String getAllEmployees(ModelMap modelMap) {
        List<Employe> all = employeeService.findAll();
        modelMap.addAttribute("employees", all);
        return "messages";
    }

    @GetMapping("/allMessages")
    public String getAllMessages(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        List<Message> all = messageService.findAllMessagesByToId(currentUser.getemploye().getId());
        modelMap.addAttribute("messages", all);
        return "showMessages";
    }

    @PostMapping("/sendMessage")
    public String sendMessage(@ModelAttribute Message message, @AuthenticationPrincipal CurrentUser currentUser) {
        message.setFromEmployee(currentUser.getemploye());
        messageService.saveMessage(message);
        return "redirect:/allMessages";
    }

}

