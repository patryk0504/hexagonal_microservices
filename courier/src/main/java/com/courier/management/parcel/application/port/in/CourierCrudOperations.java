package com.courier.management.parcel.application.port.in;

import com.courier.management.parcel.adapter.in.web.model.CourierDto;
import org.springframework.data.domain.Page;

public interface CourierCrudOperations {
    void createCourier(CourierDto courierDto);

    Page<CourierDto> getCouriers(String sortOrder, String sortBy, int page, int pageSize);

    CourierDto getCourier(long courierId);
}
