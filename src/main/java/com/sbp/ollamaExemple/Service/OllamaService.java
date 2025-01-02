package com.sbp.ollamaExemple.Service;

import com.sbp.ollamaExemple.Entity.Conversation;
import com.sbp.ollamaExemple.Repository.ConversationRepo;
import com.sbp.ollamaExemple.Repository.MessageRepo;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OllamaService {

    private final FileReadingService fileReadingService;
    private final ConversationRepo conversationRepo;
    private final MessageRepo messageRepo;
    private final OllamaChatModel chatModel;


    public OllamaService(FileReadingService fileReadingService, ConversationRepo conversationRepo, MessageRepo messageRepo, OllamaChatModel chatModel) {
        this.fileReadingService = fileReadingService;
        this.conversationRepo = conversationRepo;
        this.messageRepo = messageRepo;
        this.chatModel = chatModel;
    }

    public String askDoraAQuestion(String question) {
        String prompt = fileReadingService.readInternalFileAsString("prompts/promptDora.txt");

        List<Message> asking = new ArrayList<>();
        asking.add(new SystemMessage("<start_of_turn>" + prompt + "<end_of_turn>")) ;
        asking.add(new UserMessage("<start_of_turn>" + question + "<end_of_turn>")) ;

        Prompt promptToSend = new Prompt(asking);
        Flux<ChatResponse> chatResponses = chatModel.stream(promptToSend);
        String message = Objects.requireNonNull(chatResponses.collectList().block()).stream()
                .map(response -> response.getResult().getOutput().getContent())
                .collect(Collectors.joining("")) ;

        Conversation conv = new Conversation();
        conv.setTitle("Discussion avec Dora");
        conversationRepo.save(conv);

        com.sbp.ollamaExemple.Entity.Message mess = new com.sbp.ollamaExemple.Entity.Message();
        mess.setPrompt(question);
        mess.setReponse(message);
        mess.setConversation(conv);
        messageRepo.save(mess);

        return message;
    }

    public String getResponse(String prompt){
        List<Message> asking = new ArrayList<>();
        asking.add(new UserMessage("<start_of_turn>" + prompt + "<end_of_turn>")) ;

        Prompt promptToSend = new Prompt(asking);
        Flux<ChatResponse> chatResponses = chatModel.stream(promptToSend);
        String message = Objects.requireNonNull(chatResponses.collectList().block()).stream()
                .map(response -> response.getResult().getOutput().getContent())
                .collect(Collectors.joining("")) ;

        return message;
    }
}
