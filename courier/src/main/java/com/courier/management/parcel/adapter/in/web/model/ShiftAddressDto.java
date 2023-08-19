package com.courier.management.parcel.adapter.in.web.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShiftAddressDto {
    private AddressDto address;
    private String role;
}
