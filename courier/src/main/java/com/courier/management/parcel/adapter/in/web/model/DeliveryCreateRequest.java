package com.courier.management.parcel.adapter.in.web.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class DeliveryCreateRequest {
    @NotNull
    private DeliveryDto delivery;
    @NotNull
    private List<ShiftAddressDto> shiftAddress;
}
