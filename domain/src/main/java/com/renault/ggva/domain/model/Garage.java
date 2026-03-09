package com.renault.ggva.domain.model;

import com.renault.ggva.domain.valueobject.OpeningTime;
import lombok.Builder;
import lombok.Data;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class Garage {
    private Long id;
    private String name;
    private String address;
    private String telephone;
    private String email;
    private Map<DayOfWeek, List<OpeningTime>> horairesOuverture;
    private List<Vehicle> vehicles;

}
