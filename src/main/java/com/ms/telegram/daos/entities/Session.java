package com.ms.telegram.daos.entities;

import com.ms.telegram.models.enums.SessionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Entity
@Table(name = "sessions")
@NoArgsConstructor
@AllArgsConstructor
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "session_seq")
    @SequenceGenerator(name = "session_seq", allocationSize = 1)
    @Column(name = "ID")
    Long id;

    @Column(name = "user_id")
    Long userID;

    @Column(name = "chat_id")
    Long chatID;

    @Column(name = "status")
    SessionStatus status;

    @Column(name = "created_at")
    @CreationTimestamp
    LocalDateTime createdAt;
}
