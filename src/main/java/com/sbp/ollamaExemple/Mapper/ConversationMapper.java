package com.sbp.ollamaExemple.Mapper;

import com.sbp.ollamaExemple.Dto.ConversationDTO;
import com.sbp.ollamaExemple.Entity.Conversation;

import java.util.stream.Collectors;

public class ConversationMapper {

    public static ConversationDTO toDTO(Conversation conversation) {
        ConversationDTO dto = new ConversationDTO();
        dto.setId(conversation.getId());
        dto.setTitle(conversation.getTitle());
        dto.setMessages(
                conversation.getMessages()
                        .stream()
                        .map(MessageMapper::toMessageDTO)
                        .collect(Collectors.toList())
        );
        return dto;
    }

}
