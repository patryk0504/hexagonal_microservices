package com.courier.management.parcel.adapter.in.web;


import com.courier.management.parcel.adapter.in.web.model.CourierDto;
import com.courier.management.parcel.adapter.in.web.model.ParcelDto;
import com.courier.management.parcel.application.port.in.AssignParcelUseCase;
import com.courier.management.parcel.application.port.in.CreateCourierUseCase;
import com.courier.management.parcel.application.port.in.GetCouriersUseCase;
import com.courier.management.parcel.application.port.in.GetParcelForCourierUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("api/v1/courier")
public class CourierController {

    private final CreateCourierUseCase createCourierUseCase;
    private final GetParcelForCourierUseCase getParcels;
    private final AssignParcelUseCase assignParcelUseCase;
    private final GetCouriersUseCase getCouriersUseCase;

    @PostMapping(path = "")
    void createCourier(@RequestBody CourierDto parcelDto) {
        createCourierUseCase.createCourier(parcelDto);
    }

    @GetMapping(path = "/{courier_id}/parcels")
    Set<ParcelDto> getParcelsForCourier(
            @PathVariable("courier_id") long courierId) {
        return getParcels.getParcelsForCourier(courierId);
    }

    @GetMapping
    Page<CourierDto> getCouriers(
            @RequestParam(required = false) String sortOrder,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        return getCouriersUseCase.getCouriers(sortOrder, sortBy, page, pageSize);
    }

    @PutMapping(path = "/{courier_id}/parcel/{parcel_id}")
    ResponseEntity<Void> assignParcel(@PathVariable("courier_id") long courierId, @PathVariable("parcel_id") long parcelId) {
        assignParcelUseCase.assignParcelToCourier(courierId, parcelId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
