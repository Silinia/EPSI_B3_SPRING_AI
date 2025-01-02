package com.sbp.ollamaExemple.Repository;

import com.sbp.ollamaExemple.Entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationRepo extends JpaRepository<Conversation, Long> {
}
