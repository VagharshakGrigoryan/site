package com.itSpace.site.controllers;

import com.itSpace.site.model.Comment;
import com.itSpace.site.security.CurrentUser;
import com.itSpace.site.service.CommentService;
import com.itSpace.site.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final PostService postService;

    @PostMapping("/addComment")
    public String addComment(@ModelAttribute Comment comment, @AuthenticationPrincipal CurrentUser currentUser) {
        comment.setEmploye(currentUser.getemploye());
        comment.setCreatedDate(new Date());
        commentService.saveComment(comment);
        return "redirect:/blog/" + comment.getPost().getId();
    }

}




