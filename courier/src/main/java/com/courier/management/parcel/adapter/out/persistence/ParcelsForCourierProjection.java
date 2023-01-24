package com.courier.management.parcel.adapter.out.persistence;

import java.util.Set;

public interface ParcelsForCourierProjection {
    Set<ParcelEntity> getParcels();
}
