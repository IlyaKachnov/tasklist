package com.demo.tasklist.mapper;

import com.demo.tasklist.dao.entity.Task;
import com.demo.tasklist.dto.NotificationDto;
import com.demo.tasklist.dto.NotificationStatus;
import com.demo.tasklist.dto.TaskDto;
import com.demo.tasklist.dto.TaskPageDto;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskDto toDto(Task task);

    Task toEntity(TaskDto taskDto);

    List<TaskDto> toDtoList(List<Task> tasks);

    default TaskPageDto toPageDto(Page<Task> taskPage) {
        TaskPageDto taskPageDto = new TaskPageDto();
        taskPageDto.setTasks(toDtoList(taskPage.getContent()));
        taskPageDto.setTotalSize(taskPage.getTotalElements());
        return taskPageDto;
    }
}
