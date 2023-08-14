package com.test.hua.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.hua.model.incoming.MessageHistory;
import com.test.hua.model.outgoing.Summary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Called by the controllers as an entry point to the service layer
 */
@Component
public class MessageHistoryProvider {

        @Value("${hubspot.messages.source.url}")
        private String messagesSourceUrl;

        @Value("${hubspot.messages.target.url}")
        private String messagesTargetUrl;

        public String getMessagesSourceUrl() {
                return messagesSourceUrl;
        }

        public String getMessagesTargetUrl() {
                return messagesTargetUrl;
        }

        public String getMessageHistory() throws JsonProcessingException {

                RestTemplate restTemplate = new RestTemplate();

                // Retrieve data from source
                ResponseEntity<MessageHistory> messageHistory = restTemplate.getForEntity(messagesSourceUrl, MessageHistory.class);

                // Organize data
                MessageOrganizer organizer = new MessageOrganizer(messageHistory.getBody());
                Summary summary = organizer.getMessageSummary();

                // Second data to target
                ObjectMapper mapper = new ObjectMapper();
                String requestJson = mapper.writeValueAsString(summary);

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);

                HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
                return restTemplate.postForObject(messagesTargetUrl, entity, String.class);
        }
}
