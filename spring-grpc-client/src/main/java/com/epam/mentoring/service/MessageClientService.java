package com.epam.mentoring.service;

import com.epam.mentoring.MessageServiceGrpc;
import com.epam.mentoring.Messages;
import com.epam.mentoring.dto.MessageRequestDto;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MessageClientService {

    @GrpcClient("local-grpc-message-server")
    private MessageServiceGrpc.MessageServiceBlockingStub messageStub;

    public void sendMessage(MessageRequestDto messageRequestDto) {
        final var message = Messages.MessageRequest.newBuilder().setText(messageRequestDto.getText()).build();
        final var messageResponse = messageStub.addMessage(message);
        log.info("Message response received: messageId = {}, messageText = {}", messageResponse.getId(),
                messageResponse.getText());
    }

}
