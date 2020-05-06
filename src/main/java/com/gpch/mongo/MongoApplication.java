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

import com.gpch.mongo.iCal.iCalParser;


@Slf4j
@SpringBootApplication
public class MongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoApplication.class, args);

		/**
		 * @author Miti Mareddy
		 * NOTE:
		 * iCal4j's method CalendarBuilder is not thread-safe and needs to be
		 * run in the main thread. The workaround to this is to extend the iCalParser
		 * class with Thread in order to run the main parsing logic as a threaded
		 * subroutine. This allows iCal4j to operate without crashing.
		 *
		 * This code segment then repeatedly checks if the flag variable 'runnable' is true
		 * in which case it supplies the appropriate message to the console, once that process
		 * is done, the thread is stopped.
		 */
		iCalParser parseEvents = new iCalParser();
		parseEvents.start();
		while (parseEvents.runnable){
			System.out.println("Parsing iCal data...");
		}
		parseEvents.stop();
		System.out.println("iCalendar Thread Stopped, \"events.json\" created ...");

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
