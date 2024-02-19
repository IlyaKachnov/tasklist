package com.demo.tasklist.controller;

import com.demo.tasklist.dto.TaskDto;
import com.demo.tasklist.dto.TaskPageDto;
import com.demo.tasklist.facade.TaskFacade;
import com.demo.tasklist.mapper.TaskMapper;
import io.swagger.v3.oas.annotations.Operation;
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
    private final TaskFacade taskFacade;
    private final TaskMapper taskMapper;

    @Operation(description = "Get tasks by params - offset, page, status")
    @GetMapping
    public ResponseEntity<TaskPageDto> findTasksByParams(@RequestParam("offset") int offset, @RequestParam("size") int size, @RequestParam(value = "status", required = false) Boolean status) {
        return ResponseEntity.ok(taskMapper.toPageDto(taskFacade.findTasksPageable(offset, size, status)));
    }

    @Operation(description = "Get task by id")
    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> findTaskById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(taskMapper.toDto(taskFacade.findTaskById(id)));
    }

    @Operation(description = "Create new task")
    @PostMapping
    public ResponseEntity<TaskDto> createTask(@Validated @RequestBody TaskDto taskDto) {
        var task = taskFacade.createTask(taskMapper.toEntity(taskDto));
        return ResponseEntity.ok(taskMapper.toDto(task));
    }

    @Operation(description = "Update already existed task")
    @PutMapping
    public ResponseEntity<TaskDto> updateTask(@Validated @RequestBody TaskDto taskDto) {
        var task = taskFacade.updateTask(taskMapper.toEntity(taskDto));
        return ResponseEntity.ok(taskMapper.toDto(task));
    }

    @Operation(description = "Delete task by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable("id") Long id) {
        taskFacade.deleteTask(id);
        return ResponseEntity.ok().build();
    }
}
