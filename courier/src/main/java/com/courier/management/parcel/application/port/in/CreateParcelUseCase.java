package com.courier.management.parcel.application.port.in;

import com.courier.management.parcel.adapter.in.web.model.ParcelDto;

public interface CreateParcelUseCase {
    void createParcel(ParcelDto parcelDto);
}
