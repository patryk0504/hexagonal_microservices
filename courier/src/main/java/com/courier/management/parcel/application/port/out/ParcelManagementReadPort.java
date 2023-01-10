package com.courier.management.parcel.application.port.out;

import com.courier.management.parcel.domain.ParcelDomain;
import org.springframework.data.domain.Page;

public interface ParcelManagementReadPort {
    Page<ParcelDomain> getParcels(String sortOrder, String sortBy, int page, int pageSize);
}
