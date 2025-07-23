package com.honey.log_processing_system.log.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.honey.log_processing_system.log.dto.UserEventLogDto;
import com.honey.log_processing_system.log.entity.ActionType;
import com.honey.log_processing_system.log.entity.UserEventLog;
import com.honey.log_processing_system.log.repository.UserEventLogRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class KafkaLogConsumerTest {

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private UserEventLogRepository userEventLogRepository;

    @InjectMocks
    private KafkaLogConsumer consumer;

    @Test
    @DisplayName("사용자 행동 이벤트를 수신해서 DB에 저장한다.")
    void testConsume_ValidJsonMessage_ShouldBeSaved() throws JsonProcessingException {
        // given
        String json = """
                {
                    "userId":"honey", 
                    "action":"CLICK", 
                    "resource":"/product/123", 
                    "clientIp":"127.0.0.1", 
                    "userAgent":"Chrome", 
                    "timestamp":"2025-07-22T10:30:00"
                }
                """;

        UserEventLogDto dto = new UserEventLogDto(
                "honey",
                ActionType.CLICK,
                "/product/123",
                "127.0.0.1",
                "Chrome",
                LocalDateTime.of(2025, 7, 22, 10, 30)
        );

        UserEventLog entity = dto.toEntity();
        given(objectMapper.readValue(json, UserEventLogDto.class)).willReturn(dto);

        // when
        consumer.consume(json);

        // then
        verify(userEventLogRepository).save(entity);
    }
}