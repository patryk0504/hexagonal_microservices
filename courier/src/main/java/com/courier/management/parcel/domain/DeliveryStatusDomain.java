package com.courier.management.parcel.domain;

public enum DeliveryStatusDomain {
    IN_PROGRESS, COMPLETED;

    public static DeliveryStatusDomain fromString(String text) {
        for (DeliveryStatusDomain b : DeliveryStatusDomain.values()) {
            if (b.name().equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
