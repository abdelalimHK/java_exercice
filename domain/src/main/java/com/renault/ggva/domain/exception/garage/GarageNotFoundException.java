package com.renault.ggva.domain.exception.garage;

import com.renault.ggva.domain.exception.DomainException;

public class GarageNotFoundException extends DomainException {
    public GarageNotFoundException(Long id) {
        super("Garage not found with id: " + id);
    }
}
