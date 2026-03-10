package com.renault.ggva.controller;

import com.renault.ggva.application.port.in.garage.GetGarageUseCase;
import com.renault.ggva.application.port.in.garage.ListGaragesUseCase;
import com.renault.ggva.application.query.GarageSearchCriteria;
import com.renault.ggva.application.query.PageRequest;
import com.renault.ggva.application.query.PagedResult;
import com.renault.ggva.domain.enums.AccessoryType;
import com.renault.ggva.domain.enums.FuelType;
import com.renault.ggva.domain.model.Garage;
import com.renault.ggva.dto.response.GarageResponse;
import com.renault.ggva.dto.response.PagedResponse;
import com.renault.ggva.mapper.GarageWebMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class GarageController {

    private final GetGarageUseCase getGarageUseCase;
    private final ListGaragesUseCase listGaragesUseCase;
    private final GarageWebMapper garageWebMapper;

    @GetMapping("/garage/{id}")
    Garage getGarageById(@PathVariable Long id) {
        return getGarageUseCase.execute(id);
    }

    @GetMapping("/garages")
    public ResponseEntity<PagedResponse<GarageResponse>> list(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) FuelType fuelType,
            @RequestParam(required = false) AccessoryType accessoryType,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDirection) {

        GarageSearchCriteria criteria = new GarageSearchCriteria(
                name, city, address, fuelType, accessoryType
        );

        PageRequest pageRequest = PageRequest.of(
                page, size, sortBy, sortDirection
        );

        PagedResult<Garage> result = listGaragesUseCase.execute(
                criteria, pageRequest
        );

        PagedResponse<GarageResponse> response = PagedResponse.from(
                mapToGarageResponse(result)
        );

        return ResponseEntity.ok(response);
    }

    private PagedResult<GarageResponse> mapToGarageResponse(
            PagedResult<Garage> result) {
        List<GarageResponse> responses = result.content()
                .stream()
                .map(garageWebMapper::toResponse)
                .toList();

        return PagedResult.of(
                responses,
                result.page(),
                result.size(),
                result.totalElements()
        );
    }
}
