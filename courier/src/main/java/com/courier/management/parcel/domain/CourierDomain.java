package com.courier.management.parcel.domain;

import lombok.Builder;
import lombok.Value;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Builder
@Value
public class CourierDomain {
    Long id;
    String name;
    String email;
    String phone;
    String vehicle;
    @Builder.Default
    Set<DeliveryDomain> deliveries = Collections.emptySet();
    @Builder.Default
    Set<ParcelDomain> parcels = Collections.emptySet();
    @Builder.Default
    CourierStatusDomain status = CourierStatusDomain.AVAILABLE;
    @Builder.Default
    Set<CourierShiftAddressDomain> shiftAddress = new HashSet<>();
}
