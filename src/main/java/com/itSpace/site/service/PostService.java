package com.itSpace.site.service;

import com.itSpace.site.model.Post;
import com.itSpace.site.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<Post> findAllByEmployee_Company_Id(int id){
        return postRepository.findAllById(Collections.singleton(id));
    }
    public Optional<Post> findPostById(int id){
        return postRepository.findById(id);
    }

    public void savePost(Post post){
        postRepository.save(post);
    }
}

