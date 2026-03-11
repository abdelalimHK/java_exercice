package com.renault.ggva.infrastructur.messaging.adapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.renault.ggva.application.port.out.garage.EventPublisher;
import com.renault.ggva.domain.event.DomainEvent;
import com.renault.ggva.domain.event.VehicleAddedEvent;
import com.renault.ggva.infrastructur.messaging.mapper.EventMessageMapper;
import com.renault.ggva.infrastructur.messaging.message.VehicleAddedMessage;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class ActiveMqEventPublisherAdapter implements EventPublisher {

    private static final String VEHICLE_ADDED_QUEUE = "renault.vehicle.added";
    private static final Logger log =
            LoggerFactory.getLogger(ActiveMqEventPublisherAdapter.class);

    private final JmsTemplate jmsTemplate;
    private final ObjectMapper jmsObjectMapper;
    private final EventMessageMapper eventMessageMapper;

    @Override
    public void publish(DomainEvent event) {
        if (event instanceof VehicleAddedEvent e) {
            VehicleAddedMessage message = eventMessageMapper.toMessage(e);
            sendAsJson(VEHICLE_ADDED_QUEUE, message);
            log.info("VehicleAddedEvent published — vehicleId={}, garageId={}",
                    message.vehicleId(), message.garageId());
        }
    }

    private void sendAsJson(String queue, Object message) {
        jmsTemplate.send(queue, session -> {
            try {
                String json = jmsObjectMapper.writeValueAsString(message);
                return session.createTextMessage(json);
            } catch (JsonProcessingException e) {
                throw new JmsException("Failed to serialize message", e) {};
            }
        });
    }
}
