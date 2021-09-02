package com.itSpace.site.controllers;

import com.itSpace.site.model.Comment;
import com.itSpace.site.model.Post;
import com.itSpace.site.repository.PostRepository;
import com.itSpace.site.security.CurrentUser;
import com.itSpace.site.service.CommentService;
import com.itSpace.site.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
@Controller
@RequiredArgsConstructor

public class BlogController {

    private final PostRepository postRepository;
    private final PostService postService;
    private final CommentService commentService;

    @GetMapping("/blog")
    public String blogMain(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        Iterable<Post> posts = postService.findAllByEmployee_Company_Id(currentUser.getemploye().getCompani().getId());
        modelMap.addAttribute("posts", posts);
        return "blog-main";
    }


    @GetMapping("/blog/add")
    public String blogAdd() {
        return "blog-add";
    }

    @PostMapping("/blog/add")
    public String blogPostAdd(@AuthenticationPrincipal
       CurrentUser currentUser, @ModelAttribute Post post) {
        post.setCreatedDate(new Date());
        post.setEmploye(currentUser.getemploye());
        postService.savePost(post);
        return "redirect:/blog";

    }


    @GetMapping("/blog/{id}")
    public String blogDetals(@PathVariable(value = "id") int id, ModelMap modelMap) {
        if (!postRepository.existsById(id)) {
            return "redirect:/blog";
        }
        Optional<Post> post = postService.findPostById(id);
        ArrayList<Post> result = new ArrayList<>();
        List<Comment> comments = commentService.getAllCommentsByPostId(id);
        modelMap.addAttribute("comments",comments);
        post.ifPresent(result::add);
        modelMap.addAttribute("post", post.get());
        return "blog-detals";
    }


    @GetMapping("/blog/{id}/edit")
    public String CompEdit(@PathVariable(value = "id") int id, ModelMap modelMap) {
        if (!postRepository.existsById(id)) {
            return "redirect:/blog";
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> result = new ArrayList<>();
        post.ifPresent(result::add);
        modelMap.addAttribute("post", result);
        return "blog-edit";
    }


    @PostMapping("/blog/{id}/edit")
    public String blogPostUpdate(@PathVariable(value = "id") int id,
                                 @RequestParam String title,
                                 @RequestParam String fullText, ModelMap modelMap) {
        Post post = postRepository.findById(id).orElseThrow();
        post.setTitle(title);
        post.setFullText(fullText);
        postRepository.save(post);
        return "redirect:/blog";
    }

    @PostMapping("/blog/{id}/remove")
    public String blogPostDelet(@PathVariable(value = "id") int id, Model model) {
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
        return "redirect:/blog";
    }


}





