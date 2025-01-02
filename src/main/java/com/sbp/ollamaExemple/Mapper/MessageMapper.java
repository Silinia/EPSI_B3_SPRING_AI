package com.sbp.ollamaExemple.Mapper;

import com.sbp.ollamaExemple.Dto.MessageDTO;
import com.sbp.ollamaExemple.Entity.Message;

public class MessageMapper {

    public static MessageDTO toMessageDTO(Message message) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setPrompt(message.getPrompt());
        messageDTO.setReponse(message.getReponse());
        messageDTO.setConversationId(message.getConversation().getId());
        return messageDTO;
    }
}
