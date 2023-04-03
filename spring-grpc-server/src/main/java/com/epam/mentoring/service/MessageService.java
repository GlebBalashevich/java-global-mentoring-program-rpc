package com.epam.mentoring.service;

import java.util.Random;

import com.epam.mentoring.MessageServiceGrpc;
import com.epam.mentoring.Messages;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class MessageService extends MessageServiceGrpc.MessageServiceImplBase {

    private final Random random = new Random();

    @Override
    public void addMessage(Messages.MessageRequest request, StreamObserver<Messages.MessageResponse> responseObserver) {
        final var messageResponse = Messages.MessageResponse.newBuilder()
                .setText(request.getText())
                .setId(random.nextLong(Long.MAX_VALUE)).build();
        responseObserver.onNext(messageResponse);
        responseObserver.onCompleted();
    }
}
