package com.demo.tasklist.dao.repository;

import com.demo.tasklist.dao.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TaskRepository extends PagingAndSortingRepository<Task, Long>, CrudRepository<Task, Long> {
    Page<Task> findAllByStatus(boolean status, Pageable pageable);
}
