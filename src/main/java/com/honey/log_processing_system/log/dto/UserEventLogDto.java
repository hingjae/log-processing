package com.honey.log_processing_system.log.dto;

import com.honey.log_processing_system.log.entity.ActionType;
import com.honey.log_processing_system.log.entity.UserEventLog;

import java.time.LocalDateTime;

public record UserEventLogDto(
        String userId,
        ActionType action,
        String resource,
        String clientIp,
        String userAgent,
        LocalDateTime timestamp
) {
    public UserEventLog toEntity() {
        return UserEventLog.builder()
                .userId(userId)
                .action(action)
                .resource(resource)
                .clientIp(clientIp)
                .userAgent(userAgent)
                .timestamp(timestamp)
                .build();
    }
}
