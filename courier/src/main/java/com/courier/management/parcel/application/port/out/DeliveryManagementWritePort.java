package com.courier.management.parcel.application.port.out;

import com.courier.management.parcel.domain.CourierShiftAddressDomain;
import com.courier.management.parcel.domain.DeliveryCreateDomain;
import com.courier.management.parcel.domain.DeliveryDomain;

import java.util.List;

public interface DeliveryManagementWritePort {
    DeliveryDomain createDelivery(long courierId, DeliveryCreateDomain deliveryCreateDomain, List<CourierShiftAddressDomain> courierShiftAddressDomain);
}
