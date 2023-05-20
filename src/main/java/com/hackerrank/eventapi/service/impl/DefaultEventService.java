package com.hackerrank.eventapi.service.impl;

import com.hackerrank.eventapi.exception.BadRequestException;
import com.hackerrank.eventapi.model.Event;
import com.hackerrank.eventapi.repository.EventRepository;
import com.hackerrank.eventapi.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DefaultEventService implements EventService {
  private final EventRepository eventRepository;

  @Autowired
  DefaultEventService(EventRepository eventRepository) {
    this.eventRepository = eventRepository;
  }

  @Override
  public List<Event> getAllEvent() {
    return eventRepository.findAll();
  }

  @Override
  public Event createNewEvent(Event event) {
    if (event.getId() != null) {
      throw new BadRequestException("The ID must not be provided when creating a new Event");
    }
    return eventRepository.save(event);
  }

  @Override
  public Event getEventById(Long id) throws BadRequestException {
    if (!eventRepository.findById(id).isPresent())
      throw new BadRequestException("event id does not exist");
    return eventRepository.findById(id).get();
  }

  @Override
  public List<Event> top3By(String by) throws BadRequestException {

    switch (by) {
      case "name":
        if (eventRepository.findTop3ByOrderByNameDesc().isPresent())
          return eventRepository.findTop3ByOrderByNameDesc().get();
      case "location":
        if (eventRepository.findTop3ByOrderByLocationDesc().isPresent())
          return eventRepository.findTop3ByOrderByLocationDesc().get();
      case "cost":
        if (eventRepository.findTop3ByOrderByCostDesc().isPresent())
          return eventRepository.findTop3ByOrderByCostDesc().get();
      case "duration":
        if (eventRepository.findTop3ByOrderByDurationDesc().isPresent())
          return eventRepository.findTop3ByOrderByDurationDesc().get();
      default:
        throw new BadRequestException("Invalid attribute");
    }

  }

  @Override
  public Integer totalBy(String by) throws BadRequestException {
    List<Event> events = eventRepository.findAll();
    switch (by) {
      case "cost":
        int cost = events.stream().mapToInt(Event::getCost).sum();
        return cost;
      case "duration":
        int duration = events.stream().mapToInt(Event::getDuration).sum();
        return duration;
      default:
        throw new BadRequestException("Invalid attribute");
    }
  }
}
