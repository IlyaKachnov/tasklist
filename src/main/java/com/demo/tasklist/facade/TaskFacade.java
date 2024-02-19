package com.demo.tasklist.facade;

import com.demo.tasklist.configuration.ServerWebSocketHandler;
import com.demo.tasklist.dao.entity.Task;
import com.demo.tasklist.dto.NotificationDto;
import com.demo.tasklist.dto.NotificationStatus;
import com.demo.tasklist.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class TaskFacade {
    private final TaskService taskService;
    private final ServerWebSocketHandler webSocketHandler;
    private final ObjectMapper objectMapper;

    public Task createTask(Task task) {
        try {
            var createdTask = taskService.createTask(task);
            var notificationDto = NotificationDto.builder()
                    .taskId(createdTask.getId())
                    .status(NotificationStatus.CREATED).build();
            webSocketHandler.sendMessage(objectMapper.writeValueAsString(notificationDto));
            return task;
        } catch (IOException e) {
            log.error("Exception during processing message", e);
            throw new RuntimeException("Exception during processing message");
        }
    }

    public Task updateTask(Task task) {
        try {
            var createdTask = taskService.updateTask(task);
            var notificationDto = NotificationDto.builder()
                    .taskId(createdTask.getId())
                    .status(NotificationStatus.UPDATED).build();
            webSocketHandler.sendMessage(objectMapper.writeValueAsString(notificationDto));
            return task;
        } catch (IOException e) {
            log.error("Exception during processing message", e);
            throw new RuntimeException("Exception during processing message");
        }
    }

    public void deleteTask(Long taskId) {
        try {
            taskService.deleteTask(taskId);
            var notificationDto = NotificationDto.builder()
                    .taskId(taskId)
                    .status(NotificationStatus.DELETED).build();
            webSocketHandler.sendMessage(objectMapper.writeValueAsString(notificationDto));
        } catch (IOException e) {
            log.error("Exception during processing message", e);
            throw new RuntimeException("Exception during processing message");
        }

    }

    public Page<Task> findTasksPageable(int offset, int size, Boolean status) {
        if (status != null) {
            return taskService.findTasksPageable(offset, size, status);
        }
        return taskService.findTasksPageable(offset, size);
    }

    public Task findTaskById(Long taskId) {
        return taskService.findTaskById(taskId);
    }

}
