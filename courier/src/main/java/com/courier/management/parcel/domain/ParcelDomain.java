package com.courier.management.parcel.domain;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ParcelDomain {
    Long id;
    String name;
    String senderName;
    String senderAddress;
    String recipientName;
    String recipientAddress;
    GeoAddressDomain recipientGeoAddress;
    String weight;
    String dimensions;
    Long user;
}
