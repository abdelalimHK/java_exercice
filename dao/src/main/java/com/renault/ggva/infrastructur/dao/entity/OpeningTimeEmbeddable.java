package com.renault.ggva.infrastructur.dao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Embeddable
@Data
public class OpeningTimeEmbeddable {

    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week", nullable = false)
    private DayOfWeek dayOfWeek;

    @Column(name = "start_time", nullable = false)
    private LocalTime openingTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime closingTime;
}
