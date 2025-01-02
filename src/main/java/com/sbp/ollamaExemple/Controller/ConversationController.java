package com.sbp.ollamaExemple.Controller;

import com.sbp.ollamaExemple.Dto.ConversationDTO;
import com.sbp.ollamaExemple.Dto.MessageDTO;
import com.sbp.ollamaExemple.Entity.Conversation;
import com.sbp.ollamaExemple.Mapper.ConversationMapper;
import com.sbp.ollamaExemple.Repository.ConversationRepo;
import com.sbp.ollamaExemple.Service.ConversationService;
import com.sbp.ollamaExemple.Service.OllamaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/conversations")
public class ConversationController {

    private final ConversationService conversationService;
    private final ConversationRepo conversationRepo;
    private final OllamaService ollamaService;

    public ConversationController(ConversationService conversationService, ConversationRepo conversationRepo, OllamaService ollamaService) {
        this.conversationService = conversationService;
        this.conversationRepo = conversationRepo;
        this.ollamaService = ollamaService;
    }

    @GetMapping("/getAll")
    public List<ConversationDTO> getAllConversations() {
        List<Conversation> conversations = conversationService.getAllConversations();
        return conversations.stream()
                .map(ConversationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/create")
    public ResponseEntity<ConversationDTO> createConversation(@RequestParam String prompt) {
        ConversationDTO conversationDTO = conversationService.createConversation(prompt);
        return ResponseEntity.ok(conversationDTO);
    }

    @PostMapping("/{conversationId}/messages")
    public ResponseEntity<MessageDTO> addMessageToConversation(@PathVariable Long conversationId, @RequestParam String prompt) {
        MessageDTO messageDTO = conversationService.addMessageToConversation(conversationId, prompt);
        return ResponseEntity.ok(messageDTO);
    }
}
