package com.colak.springtutorial.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@Slf4j
public class MessageService {

    private final RestClient restClient;

    public MessageService(@Qualifier("restApi2Client") RestClient restClient) {
        this.restClient = restClient;
    }

    public void handleMessage(Message message) {
        log.info("Handling message {}", message);
        this.restClient.post().uri( "/process-message").body(message).retrieve();
    }

}
