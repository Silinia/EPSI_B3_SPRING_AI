package com.sbp.ollamaExemple.Controller;

import com.sbp.ollamaExemple.Entity.Conversation;
import com.sbp.ollamaExemple.Repository.ConversationRepo;
import com.sbp.ollamaExemple.Repository.MessageRepo;
import com.sbp.ollamaExemple.Service.ConversationService;
import com.sbp.ollamaExemple.Service.FileReadingService;
import com.sbp.ollamaExemple.Service.OllamaService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/model")
@RequiredArgsConstructor
public class OllamaCallController {

    private final OllamaService ollamaService;


    @PostMapping(path = "/askDora")
    public String askDoraAQuestion(@RequestParam String question) {
        return ollamaService.askDoraAQuestion(question);
    }

}
