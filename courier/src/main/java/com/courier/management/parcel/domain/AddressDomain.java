package com.courier.management.parcel.domain;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class AddressDomain {
    String street;
    String city;
    String state;
    String postalCode;
    String country;
    GeoAddressDomain geoAddress;
}
