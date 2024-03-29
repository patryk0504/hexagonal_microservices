package com.courier.management.parcel.application.port.in;

import com.courier.management.parcel.adapter.in.web.model.DeliveryDto;

public interface GetDeliveriesUseCase {
    DeliveryDto getCourierDeliveries(long courierId);
}
