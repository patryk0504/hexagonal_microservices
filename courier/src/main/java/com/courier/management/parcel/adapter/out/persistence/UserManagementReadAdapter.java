package com.courier.management.parcel.adapter.out.persistence;

import com.courier.management.parcel.application.port.out.UserManagementReadPort;
import com.courier.management.parcel.domain.AddressDomain;
import com.courier.management.parcel.domain.UserDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserManagementReadAdapter implements UserManagementReadPort {
    private final UserRepository userRepository;
    private final UserDomainMapper userDomainMapper;
    private final AddressDomainMapper addressDomainMapper;

    @Override
    public List<UserDomain> getUsers() {
        List<UserEntity> users = userRepository.findAll();
        return userDomainMapper.toUserDomains(users);
    }

    @Override
    public List<AddressDomain> getUserAddresses(long userId) {
        Optional<UserEntity> addressEntities = userRepository.findById(userId);
        List<AddressEntity> addressEntityList = addressEntities.orElseThrow().getAddress().stream().toList();
        return addressDomainMapper.toAddressDomainList(addressEntityList);
    }
}
