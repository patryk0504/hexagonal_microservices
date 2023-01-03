package com.courier.management.parcel.domain;

import lombok.Builder;
import lombok.Value;

import java.util.Set;

@Builder
@Value
public class UserDomain {
    Long id;
    String name;
    String email;
    String password;
    Set<ParcelDomain> parcels;
}
