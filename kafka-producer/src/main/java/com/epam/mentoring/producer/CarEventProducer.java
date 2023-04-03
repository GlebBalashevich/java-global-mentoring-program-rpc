package com.epam.mentoring.producer;

import java.util.UUID;
import java.util.function.Supplier;

import com.epam.mentoring.model.Car;
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
public class CarEventProducer {

    private final Sinks.Many<Message<Car>> processor = Sinks.many().multicast().onBackpressureBuffer();

    @Bean
    public Supplier<Flux<Message<Car>>> carProducer() {
        return processor::asFlux;
    }

    public Mono<Car> sendCarEvent(Car car) {
        final Message<Car> message = MessageBuilder.withPayload(car)
                .setHeader(KafkaHeaders.KEY, car.getId().getBytes())
                .build();
        return Mono.just(processor.tryEmitNext(message))
                .doOnNext(emitResult -> log.debug("Processing result: {}", emitResult))
                .filter(Sinks.EmitResult::isSuccess)
                .map(result -> car);
    }

}
