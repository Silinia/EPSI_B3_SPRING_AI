package com.sbp.ollamaExemple.Service;

import com.sbp.ollamaExemple.Dto.ConversationDTO;
import com.sbp.ollamaExemple.Dto.MessageDTO;
import com.sbp.ollamaExemple.Entity.Conversation;
import com.sbp.ollamaExemple.Entity.Message;
import com.sbp.ollamaExemple.Mapper.MessageMapper;
import com.sbp.ollamaExemple.Repository.ConversationRepo;
import com.sbp.ollamaExemple.Repository.MessageRepo;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConversationService {

    private final ConversationRepo conversationRepo;
    private final OllamaService ollamaService;
    private final MessageRepo messageRepo;

    @Autowired
    public ConversationService(ConversationRepo conversationRepo, FileReadingService fileReadingService, OllamaChatModel chatModel,  OllamaService ollamaService, MessageRepo messageRepo) {
        this.conversationRepo = conversationRepo;
        this.ollamaService = ollamaService;
        this.messageRepo = messageRepo;
    }

    public ConversationDTO createConversation(String prompt) {

        Conversation conv = new Conversation();

        conversationRepo.save(conv);

        String response = ollamaService.getResponse(prompt);

        Message message = new Message();
        message.setPrompt(prompt);
        message.setReponse(response);
        message.setConversation(conv);

        messageRepo.save(message);

        conv.getMessages().add(message);

        ConversationDTO conversationDTO = new ConversationDTO();
        conversationDTO.setId(conv.getId());
        conversationDTO.setTitle(conv.getTitle());
        conversationDTO.setMessages(conv.getMessages().stream().map(MessageMapper::toMessageDTO).collect(Collectors.toList()));
        return conversationDTO;
    }

    public List<Conversation> getAllConversations() {
        return conversationRepo.findAll();
    }

    public MessageDTO addMessageToConversation(Long conversationId, String prompt) {
        Conversation conversation = conversationRepo.findById(conversationId).orElseThrow(() -> new RuntimeException("Conversation with id " + conversationId + " not found"));

        String response = ollamaService.getResponse(prompt);

        Message message = new Message();
        message.setPrompt(prompt);
        message.setReponse(response);
        message.setConversation(conversation);
        messageRepo.save(message);

        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setPrompt(prompt);
        messageDTO.setReponse(response);
        messageDTO.setConversationId(conversationId);
        return messageDTO;
    }



}
