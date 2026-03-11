package com.renault.ggva.domain.exception.vehicule;

import com.renault.ggva.domain.exception.DomainException;

public class InvalidVehicleException extends DomainException {
    public InvalidVehicleException(String message) {
        super(message);
    }
}
