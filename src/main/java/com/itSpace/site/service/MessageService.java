package com.itSpace.site.service;

import com.itSpace.site.model.Message;
import com.itSpace.site.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public void saveMessage(Message message) {
        messageRepository.save(message);
    }

    public List<Message> findAllMessagesByToId(int id) {
        return messageRepository.findAllByToEmployee_Id(id);
    }
}
