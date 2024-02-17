package com.demo.tasklist.dto;

import lombok.Data;

import java.util.List;

@Data
public class TaskPageDto {
    private List<TaskDto> tasks;
    private long totalSize;
}
