package com.gpch.mongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.gpch.mongo.model.Event;
import com.gpch.mongo.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


@Slf4j
@SpringBootApplication
public class MongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoApplication.class, args);
	}

	@Bean
	public CommandLineRunner setup(EventService eventService) {
		return (args) -> {
			//log.info("Generating sample data");
			eventService.deleteAllEvents();
			List<String> events = Arrays.asList("State Wrestling", "State Volleyball", "State Frisbee", "State Cheer", "State Soccer");
			events.forEach(event ->
					eventService.saveEvent(Event.builder()
							.name(event)
							.date(LocalDateTime.now())
							.build()) );

			eventService.getAllEvents().forEach(event ->
					log.info("Event --> " + event.getName() + " ID: " + event.getId()));
		};
	}
}
