package com.courier.management.parcel.adapter.in.web;


import com.courier.management.parcel.adapter.in.web.model.CourierDto;
import com.courier.management.parcel.application.port.in.CreateCourierUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("api/v1/courier")
public class CourierController {

    private final CreateCourierUseCase createCourierUseCase;

    @PostMapping(path = "")
    void createCourier(@RequestBody CourierDto parcelDto) {
        createCourierUseCase.createCourier(parcelDto);
    }
}
