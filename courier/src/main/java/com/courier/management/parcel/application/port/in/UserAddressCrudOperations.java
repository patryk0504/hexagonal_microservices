package com.courier.management.parcel.application.port.in;

import com.courier.management.parcel.adapter.in.web.model.AddressDto;

import java.util.List;

public interface UserAddressCrudOperations {
    List<AddressDto> getUserAddresses(long userId);

    void addUserAddress(long userId, AddressDto addressDto);
}
