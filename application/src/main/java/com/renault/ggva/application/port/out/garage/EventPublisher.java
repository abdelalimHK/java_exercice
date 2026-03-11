package com.renault.ggva.application.port.out.garage;

import com.renault.ggva.domain.event.DomainEvent;

public interface EventPublisher {
    void publish(DomainEvent event);
}
