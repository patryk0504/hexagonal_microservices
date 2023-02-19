package com.courier.management.parcel.domain;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class RouteDomain {
    Long id;
    Long deliveryId;
    Long courierId;
    GeoAddressDomain address;
    int routeOrder;
}
