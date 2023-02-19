package com.courier.management.parcel.application.port.in;

import com.courier.management.parcel.adapter.in.web.model.ParcelDto;

import java.util.Set;

public interface GetParcelForCourierUseCase {
    Set<ParcelDto> getParcelsForCourier(long courierId);

    Set<ParcelDto> getParcelsForCourierByStatus(long courierId, String status);

}
