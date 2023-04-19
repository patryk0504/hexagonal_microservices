package com.courier.management.parcel.adapter.in.web;

import com.courier.management.parcel.adapter.in.web.model.AddressDto;
import com.courier.management.parcel.adapter.in.web.model.UserDto;
import com.courier.management.parcel.application.port.in.UserAddressCrudOperations;
import com.courier.management.parcel.application.port.in.UserCrudOperations;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("api/v1/users")
public class UserController {
    private final UserCrudOperations userCrudOperations;
    private final UserAddressCrudOperations userAddressCrudOperations;

    @GetMapping(path = "")
    List<UserDto> getUsers() {
        return userCrudOperations.getUsers();
    }

    @PostMapping(path = "")
    void addUser(@RequestBody UserDto userDto) {
        userCrudOperations.addUser(userDto);
    }

    @GetMapping("/{user_id}/address")
    List<AddressDto> getUserAddresses(@PathVariable("user_id") long userId) {
        return userAddressCrudOperations.getUserAddresses(userId);
    }

    @PostMapping("/{user_id}/address")
    void addUserAddress(@PathVariable("user_id") long userId, @RequestBody AddressDto addressDto) {
        userAddressCrudOperations.addUserAddress(userId, addressDto);
    }
}
