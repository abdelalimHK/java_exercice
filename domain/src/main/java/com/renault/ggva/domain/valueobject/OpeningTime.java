package com.renault.ggva.domain.valueobject;

import com.renault.ggva.domain.exception.garage.InvalidOpeningTimeException;

import java.time.LocalTime;

public record OpeningTime(LocalTime startTime, LocalTime endTime) {
    public OpeningTime {
        if (startTime.isAfter(endTime))
            throw new InvalidOpeningTimeException("Start time must be before end time");
    }
}
