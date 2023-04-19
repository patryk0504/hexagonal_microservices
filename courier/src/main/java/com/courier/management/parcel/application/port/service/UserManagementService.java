package com.courier.management.parcel.application.port.service;

import com.courier.management.parcel.adapter.in.web.mapper.AddressDtoMapper;
import com.courier.management.parcel.adapter.in.web.mapper.UserDtoMapper;
import com.courier.management.parcel.adapter.in.web.model.AddressDto;
import com.courier.management.parcel.adapter.in.web.model.UserDto;
import com.courier.management.parcel.application.port.in.UserAddressCrudOperations;
import com.courier.management.parcel.application.port.in.UserCrudOperations;
import com.courier.management.parcel.application.port.out.UserManagementReadPort;
import com.courier.management.parcel.application.port.out.UserManagementWritePort;
import com.courier.management.parcel.domain.AddressDomain;
import com.courier.management.parcel.domain.UserDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserManagementService implements UserCrudOperations, UserAddressCrudOperations {

    private final UserManagementReadPort readPort;
    private final UserManagementWritePort writePort;
    private final UserDtoMapper userDtoMapper;
    private final AddressDtoMapper addressDtoMapper;

    @Transactional(readOnly = true)
    @Override
    public List<UserDto> getUsers() {
        List<UserDomain> userDomains = readPort.getUsers();
        return userDtoMapper.toUserDtoList(userDomains);
    }

    @Override
    public void addUser(UserDto userDto) {
        UserDomain userDomain = userDtoMapper.toUserDomain(userDto);
        writePort.addUser(userDomain);
    }

    @Transactional(readOnly = true)
    @Override
    public List<AddressDto> getUserAddresses(long userId) {
        List<AddressDomain> addressDomains = readPort.getUserAddresses(userId);
        return addressDtoMapper.toAddressDto(addressDomains);
    }

    @Override
    public void addUserAddress(long userId, AddressDto addressDto) {
        AddressDomain addressDomain = addressDtoMapper.toAddressDomain(addressDto);
        writePort.addUserAddress(userId, addressDomain);
    }
}
