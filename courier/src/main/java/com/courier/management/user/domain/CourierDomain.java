package com.courier.management.user.domain;

import lombok.Builder;
import lombok.Value;

import java.util.Set;

@Builder
@Value
public class CourierDomain {
    Long id;
    String name;
    String email;
    String phone;
    String currentLocation;
    String vehicle;
    Set<DeliveryDomain> deliveries;
    Set<ParcelDomain> parcels;
    CourierStatusDomain status;
}
