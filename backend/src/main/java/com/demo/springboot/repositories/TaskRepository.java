package com.demo.springboot.repositories;

import com.demo.springboot.domain.Task;

import java.util.Optional;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface TaskRepository extends CrudRepository<Task, String> {


	@Query("select * from task where status = ?0")
	Optional<Task> findTaskByStatus(String status);


	@Query("select * from task where status = 'Pending'")
	Optional<Task> findPendingTasks();

}