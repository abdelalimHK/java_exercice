package com.renault.ggva.domain.exception.garage;

import com.renault.ggva.domain.exception.DomainException;
import com.renault.ggva.domain.valueobject.GarageId;

public class GarageCapacityExceededException extends DomainException {
    public GarageCapacityExceededException(GarageId id, int max) {
        super("Garage " + id.value() + " has reached max capacity of " + max);
    }
}
