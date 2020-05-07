package com.gpch.mongo.controller;

import com.gpch.mongo.model.Event;
import com.gpch.mongo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class EventThymeleafController {
    private EventService eventService;

    @Autowired
    public EventThymeleafController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/events-ui")
    public String events(Model model) {
        model.addAttribute("events", eventService.getAllEvents());
        return "events";
    }

    @GetMapping("/events-ui2")
    public String events2(Model model) {
        model.addAttribute("events", eventService.getAllEvents());
        return "events2";
    }

    @GetMapping("/delete-event/{id}")
    public String removeEvent(@PathVariable("id") String id, Model model) {
        eventService.deleteEventById(id);
        model.addAttribute("events", eventService.getAllEvents());
        return "events";
    }

    @GetMapping(value = {"/edit-add-event/{id}", "/edit-add-event"})
    public String editEvent(@PathVariable("id") Optional<String> id, Model model) {
        Event event = id.isPresent() ?
                eventService.findEventById(id.get()).get() : new Event();
        model.addAttribute("event", event);
        return "add-edit";
    }

    @PostMapping("/save-event")
    public String editEvent(@ModelAttribute("event") @Valid Event event,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-edit";
        }
        eventService.saveEvent(event);
        return "redirect:events-ui";
    }
}
