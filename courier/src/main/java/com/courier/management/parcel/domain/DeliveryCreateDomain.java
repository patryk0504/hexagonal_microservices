package com.courier.management.parcel.domain;

import lombok.Builder;
import lombok.Value;

import java.util.Collections;
import java.util.List;

@Builder
@Value
public class DeliveryCreateDomain {
    @Builder.Default
    List<Long> parcelIds = Collections.emptyList();
    DeliveryStatusDomain status;
    String notes;
}
