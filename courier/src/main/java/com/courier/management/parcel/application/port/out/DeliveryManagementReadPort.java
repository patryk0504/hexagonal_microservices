package com.courier.management.parcel.application.port.out;

import com.courier.management.parcel.domain.DeliveryDomain;

import java.util.Optional;

public interface DeliveryManagementReadPort {
    Optional<DeliveryDomain> getCourierDeliveries(long courierId);
}
