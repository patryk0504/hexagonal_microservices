package com.courier.management.parcel.application.port.out;

import com.courier.management.parcel.domain.AddressDomain;
import com.courier.management.parcel.domain.UserDomain;

import java.util.List;

public interface UserManagementReadPort {
    List<UserDomain> getUsers();

    List<AddressDomain> getUserAddresses(long userId);
}
