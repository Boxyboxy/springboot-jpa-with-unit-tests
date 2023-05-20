package com.hackerrank.eventapi;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import com.hackerrank.eventapi.model.Event;
import com.hackerrank.eventapi.service.impl.DefaultEventService;
import com.hackerrank.eventapi.controller.EventController;
import com.hackerrank.eventapi.exception.BadRequestException;

@ExtendWith(MockitoExtension.class)
public class EventControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Mock
  private DefaultEventService eventService;

  @Mock
  Event event;

  @InjectMocks
  private EventController eventController;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testGetEventByIdSuccess() {
    MockHttpServletRequest request = new MockHttpServletRequest();
    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    Event event = new Event(Long.valueOf(1), "event1", "X city", 1, 2);
    when(eventService.getEventById(Long.valueOf(1))).thenReturn(event);

    ResponseEntity<Object> responseEntity = eventController.getEventById(Long.valueOf(1));

    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(responseEntity.getBody()).isEqualTo(event);

  }

  @Test
  public void testGetEventByIdFailure() {
    MockHttpServletRequest request = new MockHttpServletRequest();
    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    BadRequestException e = new BadRequestException("event id does not exist");

    when(eventService.getEventById(Long.valueOf(10))).thenThrow(e);

    ResponseEntity<Object> responseEntity = eventController.getEventById(Long.valueOf(10));

    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    assertThat(responseEntity.getBody()).isEqualTo("event id does not exist");

  }

  @Test
  public void testTop3ByDurationSuccess() {
    MockHttpServletRequest request = new MockHttpServletRequest();
    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    Event event1 = new Event(Long.valueOf(7), "event7", "Y city", 4, 88);
    Event event2 = new Event(Long.valueOf(6), "event6", "R city", 2, 77);
    Event event3 = new Event(Long.valueOf(5), "event5", "Z city", 5, 6);
    List<Event> eventList = new ArrayList<Event>() {
      {
        add(event1);
        add(event2);
        add(event3);
      }
    };
    when(eventService.top3By("duration")).thenReturn(eventList);

    ResponseEntity<Object> responseEntity = eventController.getTop3EventsByAttribute("duration");

    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(responseEntity.getBody()).isEqualTo(eventList);

  }

  @Test
  public void testTop3ByFailure() {
    MockHttpServletRequest request = new MockHttpServletRequest();
    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    BadRequestException e = new BadRequestException("Invalid attribute");

    when(eventService.top3By("error")).thenThrow(e);

    ResponseEntity<Object> responseEntity = eventController.getTop3EventsByAttribute("error");

    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    assertThat(responseEntity.getBody()).isEqualTo("Invalid attribute");

  }

  @Test
  public void testTotalSumByDurationSuccess() {
    MockHttpServletRequest request = new MockHttpServletRequest();
    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

    when(eventService.totalBy("duration")).thenReturn(177);

    ResponseEntity<Object> responseEntity = eventController.getTotalByAttribute("duration");

    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(responseEntity.getBody()).isEqualTo(177);

  }

  @Test
  public void testTotalSumByFailure() {
    MockHttpServletRequest request = new MockHttpServletRequest();
    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    BadRequestException e = new BadRequestException("Invalid attribute");
    when(eventService.totalBy("error")).thenThrow(e);

    ResponseEntity<Object> responseEntity = eventController.getTotalByAttribute("error");

    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    assertThat(responseEntity.getBody()).isEqualTo("Invalid attribute");

  }

}
