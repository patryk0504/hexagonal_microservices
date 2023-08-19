package com.courier.management.parcel.adapter.in.web.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CourierAddressDto {
    private AddressDto shiftAddress;
    private String role;
}
