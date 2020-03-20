package com.demo.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import com.demo.springboot.domain.Task;
import com.demo.springboot.repositories.TaskRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

	private static final Logger log = LoggerFactory.getLogger(TaskController.class);

	@Autowired
	private TaskRepository repository;


	@RequestMapping("/api")
	public Result api() {
		return new Result("Success", "");
	}

	@RequestMapping("/")
	public Result home() {
		log.info("Init...");
		repository.deleteAll();

		final Task t = new Task("Hello");
		repository.save(t);

		return new Result("Success", "");
	}

	@RequestMapping(value = "/api/products", method = RequestMethod.GET)
	public List<Task> listTasks() {
		final List<Task> tasks = new ArrayList<>();
		repository.findAll().forEach(tasks::add);
		return tasks;
	}

	@RequestMapping(value = "/api/products/{id}", method = RequestMethod.GET)
	public Task getTaskById(@PathVariable final String id) {
		final Task task = repository.findById(id).get();
		return task;

	}

	@RequestMapping(value = "/api/products/{id}", method = RequestMethod.DELETE)
	public Result deleteTaskById(@PathVariable final String id) {
		repository.deleteById(id);
		return new Result("Success", "");
	}


	@RequestMapping(value = "/api/products", method = RequestMethod.POST)
	public Task addTask(@RequestBody final Task task) {
		log.info("Add Task with ID: " + task.getId());
		return repository.save(task);

	}

	@RequestMapping(value = "/api/products/{id}", method = RequestMethod.PUT)
	public Result completeTask(@PathVariable final String id) {
		final Task task = repository.findById(id).get();
		task.setStatus("Done");
		repository.save(task);
		return new Result("Success", "");

	}


	/**
	 * REST API response result
	 */
	public class Result {

		// private int code;
	
		private String status;
	
		private String message;
	
		public Result() {}
	
		public Result(String status, String message) {
			this.status = status;
			this.message = message;
		}
	 
		// public int getCode() {
		//     return code;
		// }
	 
		// public void setCode(int code) {
		//     this.code = code;
		// }
	
		public String getStatus() {
			return status;
		}
	
		public void setStatus(String status) {
			this.status = status;
		}
	 
		public String getMessage() {
			return message;
		}
	 
		public void setMessage(String message) {
			this.message = message;
		}
	}

}