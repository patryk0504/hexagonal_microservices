package com.courier.management.parcel.adapter.out.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
public class GeoAddress {
    private double latitude;
    private double longitude;
}