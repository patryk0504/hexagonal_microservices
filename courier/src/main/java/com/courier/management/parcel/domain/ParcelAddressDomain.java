package com.courier.management.parcel.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ParcelAddressDomain {
    AddressDomain address;
    RoleEnumDomain role;
}
