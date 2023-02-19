package com.route.application.port.out;

import com.route.adapter.in.web.model.model.ParcelDto;

import java.util.Set;

public interface GetParcelsForCourierUseCase {
    Set<ParcelDto> getParcelsForCourier(long courierId);

    Set<ParcelDto> getParcelsFilteredForCourier(long courier, String status);
}
