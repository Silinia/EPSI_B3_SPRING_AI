package com.sbp.ollamaExemple.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String prompt;

    @Column(length = 65535)
    private String reponse;

    @ManyToOne
    private Conversation conversation;


}
