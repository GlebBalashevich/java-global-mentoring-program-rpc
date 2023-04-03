package com.epam.mentoring.consumer;

import java.util.function.Consumer;

import com.epam.mentoring.model.Car;
import com.epam.mentoring.model.ExtendedCar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CarEventConsumer {

    @Bean
    public Consumer<Message<Object>> carConsumer() {
        return message -> {
            if (message.getPayload() instanceof Car car){
                log.info("Car message received: {}", car);
            }
            if (message.getPayload() instanceof ExtendedCar car){
                log.info("Extended Car message received: {}", car);
            }
        };
    }

}
