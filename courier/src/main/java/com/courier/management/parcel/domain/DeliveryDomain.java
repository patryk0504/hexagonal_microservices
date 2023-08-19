package com.courier.management.parcel.domain;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Value
public class DeliveryDomain {
    Long id;
    @Builder.Default
    List<Long> parcels = new ArrayList<>();
    Long courierId;
    LocalDateTime startTime;
    LocalDateTime endTime;
    @Builder.Default
    DeliveryStatusDomain status = DeliveryStatusDomain.IN_PROGRESS;
    String notes;
    int deliveryOrder;
}
