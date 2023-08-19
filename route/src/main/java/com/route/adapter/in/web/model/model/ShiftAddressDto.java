package com.route.adapter.in.web.model.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShiftAddressDto {
    private AddressDto address;
    private String role;
}
