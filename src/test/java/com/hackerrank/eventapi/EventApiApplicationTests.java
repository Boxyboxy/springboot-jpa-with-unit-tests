package com.hackerrank.eventapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.hackerrank.eventapi.controller.EventController;
import com.hackerrank.eventapi.model.Event;
import com.hackerrank.eventapi.service.impl.DefaultEventService;

@SpringBootTest
class EventApiApplicationTests {

	@Autowired
	private EventController eventController;

	@Test
	void contextLoads() throws Exception {
		assertThat(eventController).isNotNull();
	}

	// @Autowired
	// private MockMvc mockMvc;

	// @BeforeEach
	// public void setup() {
	// MockitoAnnotations.openMocks(this);
	// }

}
