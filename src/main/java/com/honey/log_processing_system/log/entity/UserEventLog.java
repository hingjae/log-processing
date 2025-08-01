package com.honey.log_processing_system.log.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEventLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    @Enumerated(EnumType.STRING)
    private ActionType action;

    private String resource;

    private String clientIp;

    private String userAgent;

    private LocalDateTime timestamp;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Builder
    public UserEventLog(String userId, ActionType action, String resource, String clientIp, String userAgent, LocalDateTime timestamp) {
        this.userId = userId;
        this.action = action;
        this.resource = resource;
        this.clientIp = clientIp;
        this.userAgent = userAgent;
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEventLog that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
