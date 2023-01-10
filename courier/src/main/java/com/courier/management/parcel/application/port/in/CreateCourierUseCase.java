package com.courier.management.parcel.application.port.in;

import com.courier.management.parcel.adapter.in.web.model.CourierDto;

public interface CreateCourierUseCase {
    void createCourier(CourierDto courierDto);
}
