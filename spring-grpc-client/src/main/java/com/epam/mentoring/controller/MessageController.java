package com.epam.mentoring.controller;

import com.epam.mentoring.dto.MessageRequestDto;
import com.epam.mentoring.service.MessageClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageClientService messageClientService;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addMessage(@RequestBody MessageRequestDto messageRequest) {
        messageClientService.sendMessage(messageRequest);
    }

}
