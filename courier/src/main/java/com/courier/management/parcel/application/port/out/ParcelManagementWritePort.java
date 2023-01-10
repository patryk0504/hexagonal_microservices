package com.courier.management.parcel.application.port.out;

import com.courier.management.parcel.domain.ParcelDomain;

public interface ParcelManagementWritePort {
    void createParcel(ParcelDomain parcelDomain);
}
