package com.route.adapter.in.web.model.model;

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
