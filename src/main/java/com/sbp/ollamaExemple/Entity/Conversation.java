package com.sbp.ollamaExemple.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title = "New conv";

    @OneToMany(mappedBy = "conversation")
    private List<Message> messages = new ArrayList<>();
}
