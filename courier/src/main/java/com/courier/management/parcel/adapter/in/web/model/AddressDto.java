package com.courier.management.parcel.adapter.in.web.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressDto {
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private GeoAddressDto geoAddress;

    @Data
    @NoArgsConstructor
    public static class GeoAddressDto {
        double latitude;
        double longitude;
    }

}
