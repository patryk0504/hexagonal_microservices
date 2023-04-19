package com.courier.management.parcel.application.port.out;

import com.courier.management.parcel.domain.AddressDomain;
import com.courier.management.parcel.domain.UserDomain;

public interface UserManagementWritePort {
    void addUserAddress(long userId, AddressDomain addressDomain);

    void addUser(UserDomain userDomain);
}
