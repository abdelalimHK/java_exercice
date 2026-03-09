package com.renault.ggva.resource;

import com.renault.ggva.application.port.in.garage.GetGarageUseCase;
import com.renault.ggva.domain.model.Garage;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class GarageController {

    private final GetGarageUseCase getGarageUseCase;

    @GetMapping("/hello")
    String hello() {
        return "hellllooooo";
    }

    @GetMapping("/garage/{id}")
    Garage getGarageById(@PathVariable Long id) {
        return getGarageUseCase.execute(id);
    }
}
