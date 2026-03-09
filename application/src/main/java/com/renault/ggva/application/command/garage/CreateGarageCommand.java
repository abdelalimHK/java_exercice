package com.renault.ggva.application.command.garage;

import com.renault.ggva.domain.valueobject.OpeningTime;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

public record CreateGarageCommand(
        String name,
        String address,
        String telephone,
        String email,
        Map<DayOfWeek, List<OpeningTime>> horairesOuverture
) {
}
