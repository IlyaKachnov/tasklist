package com.demo.tasklist.service.impl;

import com.demo.tasklist.dao.entity.Task;
import com.demo.tasklist.dao.repository.TaskRepository;
import com.demo.tasklist.exception.ObjectNotFoundException;
import com.demo.tasklist.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public Page<Task> findTasksPageable(int offset, int size) {
        return taskRepository.findAll(PageRequest.of(offset, size));
    }

    @Override
    public Page<Task> findTasksPageable(int offset, int size, Boolean status) {
        return taskRepository.findAllByStatus(status, PageRequest.of(offset, size));
    }

    @Override
    @Transactional(readOnly = true)
    public Task findTaskById(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new ObjectNotFoundException("Task not found by id:" + taskId));
    }


    @Override
    @Transactional
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    @Transactional
    public Task updateTask(Task task) {
        taskRepository.findById(task.getId())
                .orElseThrow(() -> new ObjectNotFoundException("Task not found by id:" + task.getId()));
        return taskRepository.save(task);
    }

    @Override
    @Transactional
    public void deleteTask(Long taskId) {
        taskRepository.findById(taskId)
                .ifPresentOrElse(task -> taskRepository.deleteById(taskId),
                        () -> new ObjectNotFoundException("Task not found by id:" + taskId)
                );
    }
}
