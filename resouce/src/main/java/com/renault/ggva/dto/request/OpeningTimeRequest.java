package com.renault.ggva.dto.request;

public record OpeningTimeRequest(
        String startTime,   // "08:00"
        String endTime      // "18:00"
) {}
