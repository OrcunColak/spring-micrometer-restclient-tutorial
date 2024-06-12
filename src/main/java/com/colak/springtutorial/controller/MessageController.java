package com.colak.springtutorial.controller;

import com.colak.springtutorial.service.Message;
import com.colak.springtutorial.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


@RestController

@RequiredArgsConstructor
@Slf4j
public class MessageController {

    private final MessageService messageService;


    // http://localhost:8080/message
    @GetMapping(value = "/message")
    public void postMessage() {
        Message message = new Message("title1", "content1");
        log.info("Received message: {}", message);
        messageService.handleMessage(message);
    }

    // We should see Header traceparent = [00-6669685c1576704fbc389775e766fe7d-3a817a928b0d37b1-00] in headers
    @PostMapping("/process-message")
    public void processMessage(
            @RequestHeader HttpHeaders headers,
            @RequestBody Message message) {

        headers.forEach((key, value) -> log.info("Header {} = {}", key, value));

        log.info("Received message for processing {}", message);
    }

}
