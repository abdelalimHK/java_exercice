package com.renault.ggva.dto.response;

import java.util.List;
import java.util.Map;

public record GarageResponse(
        String id,
        String name,
        String city,
        String address,
        String telephone,
        String email,
        Map<String, List<OpeningTimeResponse>> horairesOuverture,
        int vehicleCount
) {}
