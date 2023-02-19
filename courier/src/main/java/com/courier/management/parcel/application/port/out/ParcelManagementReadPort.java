package com.courier.management.parcel.application.port.out;

import com.courier.management.parcel.domain.ParcelDomain;
import com.courier.management.parcel.domain.ParcelStatusDomain;
import org.springframework.data.domain.Page;

import java.util.Set;

public interface ParcelManagementReadPort {
    Page<ParcelDomain> getParcels(String sortOrder, String sortBy, int page, int pageSize);

    Set<ParcelDomain> getParcelsForCourier(long courierId);

    Set<ParcelDomain> getParcelsForCourierByStatus(long courierId, ParcelStatusDomain parcelStatusDomain);
}
