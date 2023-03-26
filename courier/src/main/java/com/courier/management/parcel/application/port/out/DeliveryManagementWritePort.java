package com.courier.management.parcel.application.port.out;

import com.courier.management.parcel.domain.DeliveryCreateDomain;

public interface DeliveryManagementWritePort {
    void createDelivery(long courierId, DeliveryCreateDomain deliveryCreateDomain);
}
