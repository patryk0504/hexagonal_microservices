package com.courier.management.parcel.domain;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Builder
@Value
public class ParcelDomain {
    Long id;
    String name;
    @Builder.Default
    Set<ParcelAddressDomain> address = new HashSet<>();
    @Builder.Default
    Set<UserParcelDomain> users = new HashSet<>();
    BigDecimal weight;
    String dimensions;
    @Builder.Default
    ParcelStatusDomain status = ParcelStatusDomain.CREATED;
}
