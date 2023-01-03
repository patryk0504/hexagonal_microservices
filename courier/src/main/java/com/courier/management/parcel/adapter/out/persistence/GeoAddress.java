package com.courier.management.parcel.adapter.out.persistence;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
class GeoAddress {
    private double latitude;
    private double longitude;
}