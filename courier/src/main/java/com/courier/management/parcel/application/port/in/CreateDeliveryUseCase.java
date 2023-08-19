package com.courier.management.parcel.application.port.in;

import com.courier.management.parcel.adapter.in.web.model.DeliveryCreateRequest;
import com.courier.management.parcel.adapter.in.web.model.DeliveryDto;

public interface CreateDeliveryUseCase {
    DeliveryDto createDelivery(long courierId, DeliveryCreateRequest deliveryCreateRequest);
}
