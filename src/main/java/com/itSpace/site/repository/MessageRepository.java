package com.itSpace.site.repository;

import com.itSpace.site.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository <Message, Integer> {

        List<Message> findAllByToEmployee_Id(int id);
}