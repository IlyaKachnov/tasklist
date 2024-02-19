package com.demo.tasklist.service;

import com.demo.tasklist.dao.entity.Task;
import org.springframework.data.domain.Page;

public interface TaskService {

    Page<Task> findTasksPageable(int offset, int size);

    Page<Task> findTasksPageable(int offset, int size, Boolean status);

    Task findTaskById(Long taskId);

    Task createTask(Task task);

    Task updateTask(Task task);

    void deleteTask(Long taskId);
}
