package com.courier.management.user.domain;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class RouteDomain {
    Long id;
    DeliveryDomain delivery;
    String location;
    String notes;
}
