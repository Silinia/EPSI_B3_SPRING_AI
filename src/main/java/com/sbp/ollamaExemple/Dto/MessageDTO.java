package com.sbp.ollamaExemple.Dto;

import com.sbp.ollamaExemple.Entity.Conversation;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MessageDTO {

    private String prompt;

    private String reponse;

    private Long conversationId;


}
