package com.courier.management.parcel.application.port.out;

import com.courier.management.parcel.domain.CourierDomain;
import org.springframework.data.domain.Page;

public interface CourierManagementReadPort {
    Page<CourierDomain> getCouriers(String sortOrder, String sortBy, int page, int pageSize);
}
