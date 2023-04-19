package com.courier.management.parcel.application.port.out;

import com.courier.management.parcel.domain.CourierDomain;

import java.util.List;

public interface CourierManagementWritePort {
    void createCourier(CourierDomain courierDomain);

    void assignParcel(long courierId, List<Long> parcelIds);
}
