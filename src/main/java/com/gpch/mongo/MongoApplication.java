package com.gpch.mongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gpch.mongo.model.Email;
import com.gpch.mongo.model.Event;
import com.gpch.mongo.model.Job;
import com.gpch.mongo.model.Phone;
import com.gpch.mongo.service.EmailService;
import com.gpch.mongo.service.EventService;
import com.gpch.mongo.service.JobService;
import com.gpch.mongo.service.PhoneService;
import com.gpch.mongo.service.UserJobService;
import com.gpch.mongo.service.UserService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;

import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.gpch.mongo.model.UserJob;
import com.gpch.mongo.repository.EventRepository;
import com.gpch.mongo.model.User;


@Slf4j
@SpringBootApplication
public class MongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoApplication.class, args);
	}

	@Bean
	public CommandLineRunner setup(EventService eventService, UserService userService, PhoneService phoneService, 
			EmailService emailService, UserJobService userJobService, JobService jobService) {
		return (args) -> {
			//log.info("Generating sample data");
			eventService.deleteAllEvents();
			//List<String> events = Arrays.asList("Bob", "Peter", "Gus", "John", "David");
			/*events.forEach(event ->
					eventService.saveEvent(Event.builder()
							.event_name(event)
							.date(LocalDateTime.now())
							.build()) );
			*/
			
		};
	}
}
