package com.renault.ggva.domain.exception.garage;

import com.renault.ggva.domain.exception.DomainException;

public class InvalidOpeningTimeException extends DomainException {
    public InvalidOpeningTimeException(String message) {
        super(message);
    }
}
