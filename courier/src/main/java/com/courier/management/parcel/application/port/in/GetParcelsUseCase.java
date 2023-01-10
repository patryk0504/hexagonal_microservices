package com.courier.management.parcel.application.port.in;

import com.courier.management.parcel.adapter.in.web.model.ParcelDto;
import org.springframework.data.domain.Page;

public interface GetParcelsUseCase {
    Page<ParcelDto> getParcels(String sortOrder, String sortBy, int page, int pageSize);
}
