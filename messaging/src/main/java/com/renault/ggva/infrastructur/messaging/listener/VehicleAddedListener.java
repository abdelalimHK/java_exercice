package com.renault.ggva.infrastructur.messaging.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.renault.ggva.infrastructur.messaging.mapper.EventMessageMapper;
import com.renault.ggva.domain.event.VehicleAddedEvent;
import com.renault.ggva.infrastructur.messaging.message.VehicleAddedMessage;
import jakarta.jms.JMSException;
import jakarta.jms.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class VehicleAddedListener {

    private static final Logger log =
            LoggerFactory.getLogger(VehicleAddedListener.class);

    private final ObjectMapper jmsObjectMapper;
    private final EventMessageMapper eventMessageMapper;

    public VehicleAddedListener(ObjectMapper jmsObjectMapper,
                                EventMessageMapper eventMessageMapper) {
        this.jmsObjectMapper = jmsObjectMapper;
        this.eventMessageMapper = eventMessageMapper;
    }

    @JmsListener(
            destination = "renault.vehicle.added",
            containerFactory = "jmsFactory"
    )
    public void onVehicleAdded(TextMessage textMessage) throws JMSException {
        try {
            String json = textMessage.getText();
            VehicleAddedMessage message = jmsObjectMapper.readValue(
                    json, VehicleAddedMessage.class
            );

            log.info("VehicleAddedEvent consumed — vehicleId={}, garageId={}, " +
                            "brand={}, model={}",
                    message.vehicleId(),
                    message.garageId(),
                    message.brand(),
                    message.model()
            );

            handleVehicleAdded(message);

        } catch (JsonProcessingException e) {
            log.error("Failed to deserialize VehicleAddedMessage: {}",
                    e.getMessage(), e);
            throw new JMSException("Deserialization failed: " + e.getMessage());
        }
    }

    private void handleVehicleAdded(VehicleAddedMessage message) {
        VehicleAddedEvent event = eventMessageMapper.toDomainEvent(message);
        log.info("Vehicle processed — id={}, brand={}, model={}",
                event.vehicleId() != null ? event.vehicleId() : "unknown",
                event.brand(),
                event.model()
        );
    }
}
