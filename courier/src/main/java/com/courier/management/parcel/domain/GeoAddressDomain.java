package com.courier.management.parcel.domain;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class GeoAddressDomain {
    double latitude;
    double longitude;
}
