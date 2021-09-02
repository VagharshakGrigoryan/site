package com.itSpace.site.repository;

import com.itSpace.site.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository <Comment,Integer> {
    List<Comment> findAllByEmploye_Compani_Id(int id);
}


