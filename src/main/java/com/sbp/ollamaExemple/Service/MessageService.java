package com.sbp.ollamaExemple.Service;

import com.sbp.ollamaExemple.Entity.Message;
import com.sbp.ollamaExemple.Repository.MessageRepo;

import java.util.List;

public class MessageService {

    private final MessageRepo messageRepo;

    public MessageService(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    public List<Message> getAllMessages() {
        return messageRepo.findAll();
    }

    public Message createMessage(Message message) {
        return messageRepo.save(message);
    }
}
