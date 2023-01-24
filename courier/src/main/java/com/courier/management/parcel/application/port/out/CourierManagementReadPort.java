package com.courier.management.parcel.application.port.out;

import com.courier.management.parcel.domain.CourierDomain;
import com.courier.management.parcel.domain.ParcelDomain;
import org.springframework.data.domain.Page;

import java.util.Set;

public interface CourierManagementReadPort {
    Set<ParcelDomain> getParcelsForCourier(long courierId);

    Page<CourierDomain> getCouriers(String sortOrder, String sortBy, int page, int pageSize);
}
