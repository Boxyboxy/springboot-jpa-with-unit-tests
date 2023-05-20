package com.hackerrank.eventapi.service;

import com.hackerrank.eventapi.model.Event;
import java.util.List;

public interface EventService {

  List<Event> getAllEvent();

  Event createNewEvent(Event event);

  Event getEventById(Long id);

  List<Event> top3By(String by);

  Integer totalBy(String by);
}
