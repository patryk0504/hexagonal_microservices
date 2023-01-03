package com.courier.management.user.domain;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ParcelDomain {
    Long id;
    String name;
    String weight;
    String dimensions;
    String notes;
}
