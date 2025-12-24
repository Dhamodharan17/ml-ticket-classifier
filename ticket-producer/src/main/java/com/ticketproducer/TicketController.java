package com.ticketproducer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import tools.jackson.databind.ObjectMapper;


import java.util.Map;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${kafka.topic.raw}")
    private String rawTopic;

    public TicketController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping
    public ResponseEntity<String> createTicket(@RequestBody Map<String, String> request)
            throws JsonProcessingException {

        kafkaTemplate.send(rawTopic, objectMapper.writeValueAsString(request));
        return ResponseEntity.ok("Ticket sent for classification");
    }
}
