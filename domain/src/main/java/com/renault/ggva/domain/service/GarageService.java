package com.renault.ggva.domain.service;

import com.renault.ggva.domain.model.Garage;
import com.renault.ggva.domain.valueobject.OpeningTime;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

public class GarageService {

    public Garage createGarage(String name, String address, String telephone,
                               String email,
                               Map<DayOfWeek, List<OpeningTime>> horairesOuverture) {
//        validateName(name);
//        validateAddress(address);
//        validateTelephone(telephone);
//        validateEmail(email);
//        validateHoraires(horairesOuverture);

        return Garage.builder()
                .name(name)
                .address(address)
                .telephone(telephone)
                .email(email)
                .build();
    }
}
