package com.courier.management.parcel.domain;

public enum ParcelStatusDomain {
    CREATED, IN_TRANSIT, DELIVERED, RETURNED;

    public static ParcelStatusDomain fromString(String text) {
        for (ParcelStatusDomain b : ParcelStatusDomain.values()) {
            if (b.name().equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
