package com.courier.management.parcel.domain;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Builder
@Value
public class DeliveryDomain {
    Long id;
    ParcelDomain parcel;
    Long courierId;
    LocalDateTime startTime;
    LocalDateTime endTime;
    @Builder.Default
    DeliveryStatusDomain status = DeliveryStatusDomain.IN_PROGRESS;
    String notes;
    @Builder.Default
    List<RouteDomain> routes = Collections.emptyList();
}
