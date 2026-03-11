package com.renault.ggva.controller;

import com.renault.ggva.application.port.in.garage.GetGarageUseCase;
import com.renault.ggva.application.port.in.garage.ListGaragesUseCase;
import com.renault.ggva.application.port.in.vehicule.AddVehicleUseCase;
import com.renault.ggva.application.query.GarageSearchCriteria;
import com.renault.ggva.application.query.PageRequest;
import com.renault.ggva.application.query.PagedResult;
import com.renault.ggva.domain.enums.AccessoryType;
import com.renault.ggva.domain.enums.FuelType;
import com.renault.ggva.domain.model.Garage;
import com.renault.ggva.domain.model.Vehicle;
import com.renault.ggva.dto.request.AddVehicleRequest;
import com.renault.ggva.dto.response.GarageResponse;
import com.renault.ggva.dto.response.PagedResponse;
import com.renault.ggva.dto.response.VehicleResponse;
import com.renault.ggva.mapper.GarageWebMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/garages")
@AllArgsConstructor
public class GarageController {

    private final GetGarageUseCase getGarageUseCase;
    private final ListGaragesUseCase listGaragesUseCase;
    private final AddVehicleUseCase addVehicleUseCase;
    private final GarageWebMapper garageWebMapper;

    @GetMapping("/{id}")
    Garage getGarageById(@PathVariable Long id) {
        return getGarageUseCase.execute(id);
    }

    @GetMapping
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

    @PostMapping("/{garageId}/vehicles")
    @ResponseStatus(HttpStatus.CREATED)
    public VehicleResponse addVehicle(@PathVariable Long garageId,
                                      @RequestBody AddVehicleRequest request) {
        Vehicle vehicle = addVehicleUseCase.execute(
                garageWebMapper.toAddVehicleCommand(garageId, request)
        );
        return garageWebMapper.toVehicleResponse(vehicle);
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
