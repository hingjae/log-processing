package com.honey.log_processing_system.log.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.honey.log_processing_system.log.dto.UserEventLogDto;
import com.honey.log_processing_system.log.entity.UserEventLog;
import com.honey.log_processing_system.log.repository.UserEventLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaLogConsumer {

    private final ObjectMapper objectMapper;
    private final UserEventLogRepository userEventLogRepository;

    @Transactional
    @KafkaListener(topics = "user-event-log", groupId = "log-consumer")
    public void consume(String message) {
        try {
            UserEventLogDto dto = objectMapper.readValue(message, UserEventLogDto.class);

            UserEventLog entity = dto.toEntity();

            userEventLogRepository.save(entity);
        } catch (JsonProcessingException e) {
            log.error("Failed to parse message : {}", message);
        }
    }
}
