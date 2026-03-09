package com.renault.ggva.domain.exception.garage;

import com.renault.ggva.domain.exception.DomainException;

public class InvalidGarageException extends DomainException {
    public InvalidGarageException(String message) {
        super(message);
    }
}
