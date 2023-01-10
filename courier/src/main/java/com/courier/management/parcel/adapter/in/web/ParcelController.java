package com.courier.management.parcel.adapter.in.web;

import com.courier.management.parcel.adapter.in.web.model.ParcelDto;
import com.courier.management.parcel.application.port.in.CreateParcelUseCase;
import com.courier.management.parcel.application.port.in.GetParcelsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("api/v1/parcel")
class ParcelController {
    private final CreateParcelUseCase createParcelUseCase;
    private final GetParcelsUseCase getParcelsUseCase;

    @PostMapping(path = "")
    void createParcel(@RequestBody ParcelDto parcelDto) {
        createParcelUseCase.createParcel(parcelDto);
    }

    @GetMapping(path = "")
    Page<ParcelDto> getParcels(@RequestParam(required = false) String sortOrder,
                               @RequestParam(defaultValue = "name") String sortBy,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int pageSize) {
        return getParcelsUseCase.getParcels(sortOrder, sortBy, page, pageSize);
    }
}
