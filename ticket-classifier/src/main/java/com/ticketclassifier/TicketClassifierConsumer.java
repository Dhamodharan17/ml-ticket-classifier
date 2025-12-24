package com.ticketclassifier;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.ObjectMapper;


import java.util.Map;


@Component
public class TicketClassifierConsumer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper mapper = new ObjectMapper();

    @Value("${kafka.topic.classified}")
    private String classifiedTopic;

    @Value("${ml.service.url}")
    private String mlUrl;

    public TicketClassifierConsumer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "${kafka.topic.raw}")
    public void consume(String message) throws Exception {

        Map<String, String> ticket = mapper.readValue(message, Map.class);

        Map<String, String> mlReq = Map.of("text", ticket.get("message"));
        Map mlResp = restTemplate.postForObject(mlUrl, mlReq, Map.class);

        ticket.put("category", mlResp.get("category").toString());

        kafkaTemplate.send(classifiedTopic, mapper.writeValueAsString(ticket));
        System.out.println("Classified Ticket → " + ticket);
    }
}
