package com.courier.management.user.domain;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Value
public class DeliveryDomain {
    Long id;
    ParcelDomain parcel;
    CourierDomain courier;
    LocalDateTime startTime;
    LocalDateTime endTime;
    DeliveryStatusDomain status;
    String notes;
    List<RouteDomain> routes;
}
