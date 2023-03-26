package com.courier.management.parcel.application.port.out;

import com.courier.management.parcel.domain.DeliveryDomain;

import java.util.Set;

public interface DeliveryManagementReadPort {
    Set<DeliveryDomain> getCourierDeliveries(long courierId);
}
