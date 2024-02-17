package com.demo.tasklist.controller;

import com.demo.tasklist.dto.TaskDto;
import com.demo.tasklist.dto.TaskPageDto;
import com.demo.tasklist.mapper.TaskMapper;
import com.demo.tasklist.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @GetMapping
    public ResponseEntity<TaskPageDto> findTasksByPage(@RequestParam("offset") int offset, @RequestParam("size") int size) {
        return ResponseEntity.ok(taskMapper.toPageDto(taskService.findTasksPageable(offset, size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> findTaskById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(taskMapper.toDto(taskService.findTaskById(id)));
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@Validated @RequestBody TaskDto taskDto) {
        var task = taskService.createTask(taskMapper.toEntity(taskDto));
        return ResponseEntity.ok(taskMapper.toDto(task));
    }

    @PutMapping
    public ResponseEntity<TaskDto> updateTask(@Validated @RequestBody TaskDto taskDto) {
        var task = taskService.updateTask(taskMapper.toEntity(taskDto));
        return ResponseEntity.ok(taskMapper.toDto(task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }
}
