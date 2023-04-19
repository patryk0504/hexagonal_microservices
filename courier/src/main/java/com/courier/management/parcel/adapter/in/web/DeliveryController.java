package com.courier.management.parcel.adapter.in.web;

import com.courier.management.parcel.adapter.in.web.model.DeliveryDto;
import com.courier.management.parcel.application.port.in.CreateDeliveryUseCase;
import com.courier.management.parcel.application.port.in.GetDeliveriesUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("api/v1/deliveries")
public class DeliveryController {
    private final CreateDeliveryUseCase createDeliveryUseCase;
    private final GetDeliveriesUseCase getDeliveriesUseCase;

    @PostMapping(path = "/courier/{courier_id}")
    void createDelivery(@PathVariable("courier_id") long courierId, @RequestBody DeliveryDto deliveryDto) {
        createDeliveryUseCase.createDelivery(courierId, deliveryDto);
    }

    //TODO add some filtering maybe by status or something
    @GetMapping(path = "/courier/{courier_id}")
    DeliveryDto getCourierDeliveries(@PathVariable("courier_id") long courierId) {
        return getDeliveriesUseCase.getCourierDeliveries(courierId);
    }
}
