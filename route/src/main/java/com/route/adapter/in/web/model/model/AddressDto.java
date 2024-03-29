package com.route.adapter.in.web.model.model;

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
    private String simpleAddress;
    private GeoAddressDto geoAddress;

    @Data
    @NoArgsConstructor
    public static class GeoAddressDto {
        double latitude;
        double longitude;
    }
}
