package com.demo.tasklist.dao.repository;

import com.demo.tasklist.dao.entity.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TaskRepository extends PagingAndSortingRepository<Task, Long>, CrudRepository<Task, Long> {
    List<Task> findAll();

    List<Task> findAllByStatus(boolean status);
}
