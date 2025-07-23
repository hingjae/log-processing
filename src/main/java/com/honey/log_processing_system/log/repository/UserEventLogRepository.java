package com.honey.log_processing_system.log.repository;

import com.honey.log_processing_system.log.entity.UserEventLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEventLogRepository extends JpaRepository<UserEventLog, Long> {
}
