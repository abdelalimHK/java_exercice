package com.renault.ggva.infrastructur.messaging.mapper;

import com.renault.ggva.domain.event.VehicleAddedEvent;
import com.renault.ggva.infrastructur.messaging.message.VehicleAddedMessage;
import org.springframework.stereotype.Component;

@Component
public class EventMessageMapper {

    public VehicleAddedMessage toMessage(VehicleAddedEvent event) {
        return new VehicleAddedMessage(
                event.vehicleId(),
                event.garageId(),
                event.brand(),
                event.model(),
                event.occurredAt()
        );
    }

    public VehicleAddedEvent toDomainEvent(VehicleAddedMessage message) {

        return new VehicleAddedEvent(
                message.vehicleId(),
                message.garageId(),
                message.brand(),
                message.model(),
                null,
                0,
                message.occurredAt()
        );
    }
}
