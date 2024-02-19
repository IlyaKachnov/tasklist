package com.demo.tasklist.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationDto {
    private Long taskId;
    private NotificationStatus status;
}
