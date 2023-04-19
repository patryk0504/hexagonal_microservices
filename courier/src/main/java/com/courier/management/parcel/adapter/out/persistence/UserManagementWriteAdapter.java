package com.courier.management.parcel.adapter.out.persistence;

import com.courier.management.parcel.application.port.out.UserManagementWritePort;
import com.courier.management.parcel.domain.AddressDomain;
import com.courier.management.parcel.domain.UserDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class UserManagementWriteAdapter implements UserManagementWritePort {
    private final UserRepository userRepository;
    private final AddressDomainMapper addressDomainMapper;
    private final UserDomainMapper userDomainMapper;

    @Override
    public void addUserAddress(long userId, AddressDomain addressDomain) {
        AddressEntity addressEntity = addressDomainMapper.toAddressEntity(addressDomain);
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        userEntity.orElseThrow().addAddress(addressEntity);
    }

    @Override
    public void addUser(UserDomain userDomain) {
        UserEntity userEntity = userDomainMapper.toUserEntity(userDomain);
        userRepository.save(userEntity);
    }
}
