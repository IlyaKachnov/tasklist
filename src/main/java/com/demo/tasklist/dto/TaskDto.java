package com.demo.tasklist.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TaskDto {
    private Long id;
    @NotBlank(message = "Name must not be null or empty")
    private String name;
    private String description;
    private boolean status;
}
