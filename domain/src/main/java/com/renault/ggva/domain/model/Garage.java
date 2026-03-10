package com.renault.ggva.domain.model;

import com.renault.ggva.domain.valueobject.OpeningTime;
import lombok.Builder;
import lombok.Data;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class Garage {
    private Long id;
    private String name;
    private String city;
    private String address;
    private String telephone;
    private String email;
    private Map<DayOfWeek, List<OpeningTime>> horairesOuverture;
    @Builder.Default
    private List<Vehicle> vehicles = new ArrayList<>();

}
