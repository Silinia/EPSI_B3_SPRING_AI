package com.sbp.ollamaExemple.Repository;

import com.sbp.ollamaExemple.Entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message, Long> {
}
