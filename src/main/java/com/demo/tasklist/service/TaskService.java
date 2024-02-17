package com.demo.tasklist.service;

import com.demo.tasklist.dao.entity.Task;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TaskService {
    List<Task> findAllTasks();

    Page<Task> findTasksPageable(int offset, int size);

    Task findTaskById(Long taskId);

    List<Task> findTasksByStatus(boolean status);

    Task createTask(Task task);

    Task updateTask(Task task);

    void deleteTask(Long taskId);
}
