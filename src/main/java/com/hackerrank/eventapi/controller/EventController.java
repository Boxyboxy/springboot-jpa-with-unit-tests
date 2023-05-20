package com.hackerrank.eventapi.controller;

import com.hackerrank.eventapi.exception.BadRequestException;
import com.hackerrank.eventapi.model.Event;
import com.hackerrank.eventapi.service.EventService;
import com.hackerrank.eventapi.service.impl.DefaultEventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(path = "/event", produces = MediaType.APPLICATION_JSON_VALUE)
public class EventController {
  @Autowired
  private DefaultEventService eventService;

  // @Autowired
  // public EventController(DefaultEventService eventService) {
  // this.eventService = eventService;
  // }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Event> getAllEvent() {
    return eventService.getAllEvent();
  }

  @GetMapping(path = "{id}")
  public ResponseEntity<Object> getEventById(@PathVariable Long id) {
    try {
      System.out.println(ResponseEntity.ok(eventService.getEventById(id)));
      System.out.println(ResponseEntity.ok(eventService.getEventById(id)).getBody());
      return ResponseEntity.ok(eventService.getEventById(id));
    } catch (BadRequestException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

  }

  @GetMapping(path = "top3")
  public ResponseEntity<Object> getTop3EventsByAttribute(@RequestParam("by") String givenField) {
    try {
      return ResponseEntity.ok(eventService.top3By(givenField));

    } catch (BadRequestException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

  }

  @GetMapping(path = "total")
  public ResponseEntity<Object> getTotalByAttribute(@RequestParam("by") String givenField) {
    try {
      return ResponseEntity.ok(eventService.totalBy(givenField));
    } catch (BadRequestException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Event createEvent(@RequestBody Event event) {
    return eventService.createNewEvent(event);
  }
}
