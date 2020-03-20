package com.demo.springboot.controller;

import com.demo.springboot.domain.Task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class ToDoController {

	private static final Logger log = LoggerFactory.getLogger(ToDoController.class);

	@Value(value="${backend.host}")
	private String host;

	@Value(value="${backend.port}")
	private String port;

    @Autowired
    private RestTemplate restTemplate;


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {

		model.addAttribute("task", new Task());

		String uri = String.format("http://%s:%s/api/products", host, port);
		log.info("URI :" + uri);
		
		Task[] tasks = restTemplate.getForObject(uri, Task[].class);
		
		log.info("Total Size: " + tasks.length);

		model.addAttribute("tasks", tasks);

		return "index";
	}



	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addTask(@ModelAttribute Task task) {

		log.info(String.format("Add Task %s - %s", task.getContent(), task.getId()));

		String uri = String.format("http://%s:%s/api/products", host, port);
		log.info("URI :" + uri);

		restTemplate.postForObject(
			uri,
			task,
			Task.class);

		// URI url = new URI(backendUri);

        // HttpHeaders httpHeaders = new HttpHeaders();
        // httpHeaders.set("Content-Type", "application/json");
        // HttpEntity<GuestBookEntry> httpEntity =
        //     new HttpEntity<GuestBookEntry>(formMessage, httpHeaders);
        // RestTemplate restTemplate = new RestTemplate();
        // restTemplate.postForObject(url, httpEntity, String.class);

		return "redirect:/";
	}


	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteTask(@RequestParam(name="id", required=true, defaultValue="0") String id) {


		String uri = String.format("http://%s:%s/api/products/%s", host, port, id);
		log.info("URI :" + uri);
		
		restTemplate.delete(uri);
		
		return "redirect:/";
		
	}


	@RequestMapping(value = "/complete", method = RequestMethod.GET)
	public String completeTask(@RequestParam(name="id", required=true, defaultValue="0") String id) {


		String uri = String.format("http://%s:%s/api/products/%s", host, port, id);
		log.info("URI :" + uri);
		
		restTemplate.put(uri, null );
		
		return "redirect:/";
		
	}

}