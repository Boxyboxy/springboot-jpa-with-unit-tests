package com.hackerrank.eventapi.repository;

import com.hackerrank.eventapi.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

  interface DurationOnly {
    Integer getDuration();
  }

  Optional<List<Event>> findTop3ByOrderByNameDesc();

  Optional<List<Event>> findTop3ByOrderByLocationDesc();

  Optional<List<Event>> findTop3ByOrderByCostDesc();

  Optional<List<Event>> findTop3ByOrderByDurationDesc();

}
