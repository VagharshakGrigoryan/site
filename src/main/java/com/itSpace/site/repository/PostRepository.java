package com.itSpace.site.repository;


import com.itSpace.site.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findAllByEmploye_Compani_Id(int id);
}
