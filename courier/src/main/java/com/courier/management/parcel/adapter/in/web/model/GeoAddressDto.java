package com.courier.management.parcel.adapter.in.web.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GeoAddressDto {
    private double latitude;
    private double longitude;
}
