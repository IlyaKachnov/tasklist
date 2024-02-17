package com.demo.tasklist.dto;

import lombok.Data;

@Data
public class TaskDto {
    private Long id;
    private String name;
    private String description;
    private boolean status;
}
