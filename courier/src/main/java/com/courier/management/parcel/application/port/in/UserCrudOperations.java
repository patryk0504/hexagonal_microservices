package com.courier.management.parcel.application.port.in;

import com.courier.management.parcel.adapter.in.web.model.UserDto;

import java.util.List;

public interface UserCrudOperations {
    List<UserDto> getUsers();

    void addUser(UserDto userDto);
}
