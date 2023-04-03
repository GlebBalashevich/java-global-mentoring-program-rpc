package com.epam.mentoring.producer;

import java.util.UUID;
import java.util.function.Supplier;

import com.epam.mentoring.model.CarType;
import com.epam.mentoring.model.EngineType;
import com.epam.mentoring.model.ExtendedCar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@Slf4j
@Component
public class ExtendedCarEventProducer {

    private final Sinks.Many<Message<ExtendedCar>> processor = Sinks.many().multicast().onBackpressureBuffer();

    @Bean
    public Supplier<Flux<Message<ExtendedCar>>> extendedCarProducer() {
        return processor::asFlux;
    }

    public Mono<ExtendedCar> sendCarEvent(ExtendedCar extendedCar) {
        final Message<ExtendedCar> message = MessageBuilder.withPayload(extendedCar)
                .setHeader(KafkaHeaders.KEY, extendedCar.getId().getBytes())
                .build();
        return Mono.just(processor.tryEmitNext(message))
                .doOnNext(emitResult -> log.debug("Processing result: {}", emitResult))
                .filter(Sinks.EmitResult::isSuccess)
                .map(result -> extendedCar);
    }

}
