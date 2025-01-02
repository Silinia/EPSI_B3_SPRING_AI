package com.sbp.ollamaExemple.Dto;

import com.sbp.ollamaExemple.Entity.Message;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ConversationDTO {

    private Long id;
    private String title;
    private List<MessageDTO> messages;

}
