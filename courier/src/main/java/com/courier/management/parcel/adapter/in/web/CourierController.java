package com.courier.management.parcel.adapter.in.web;


import com.courier.management.parcel.adapter.in.web.model.AssignParcelDto;
import com.courier.management.parcel.adapter.in.web.model.CourierDto;
import com.courier.management.parcel.adapter.in.web.model.ParcelDto;
import com.courier.management.parcel.application.port.in.AssignParcelUseCase;
import com.courier.management.parcel.application.port.in.CourierCrudOperations;
import com.courier.management.parcel.application.port.in.GetParcelForCourierUseCase;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("api/v1/couriers")
public class CourierController {

    private final CourierCrudOperations courierCrudOperations;
    private final GetParcelForCourierUseCase getParcels;
    private final AssignParcelUseCase assignParcelUseCase;

    @PostMapping(path = "")
    void createCourier(@RequestBody CourierDto parcelDto) {
        courierCrudOperations.createCourier(parcelDto);
    }

    @GetMapping(path = "/{courier_id}/parcels/{status}")
    Set<ParcelDto> getParcelsForCourier(
            @PathVariable("courier_id") long courierId, @PathVariable(value = "status", required = false) String status) {
        if (StringUtils.isEmpty(status)) {
            return getParcels.getParcelsForCourier(courierId);
        } else {
            return getParcels.getParcelsForCourierByStatus(courierId, status);
        }
    }

    @GetMapping(path = "/{courier_id}/parcels")
    Set<ParcelDto> getAllParcelsForCourier(
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
        return courierCrudOperations.getCouriers(sortOrder, sortBy, page, pageSize);
    }

    @PutMapping(path = "/{courier_id}/parcel")
    ResponseEntity<Void> assignParcel(@PathVariable("courier_id") long courierId, @RequestBody AssignParcelDto assignParcelDto) {
        assignParcelUseCase.assignParcelToCourier(courierId, assignParcelDto.getSelectedParcelIds());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/{courier_id}")
    CourierDto getCourier(@PathVariable("courier_id") long courierId) {
        return courierCrudOperations.getCourier(courierId);
    }
}
