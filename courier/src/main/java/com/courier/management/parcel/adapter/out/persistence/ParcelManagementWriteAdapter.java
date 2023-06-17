package com.courier.management.parcel.adapter.out.persistence;

import com.courier.management.parcel.adapter.out.persistence.entity.AddressEntity;
import com.courier.management.parcel.adapter.out.persistence.entity.ParcelEntity;
import com.courier.management.parcel.adapter.out.persistence.entity.RoleEnum;
import com.courier.management.parcel.adapter.out.persistence.entity.UserEntity;
import com.courier.management.parcel.adapter.out.persistence.mapper.AddressDomainMapper;
import com.courier.management.parcel.adapter.out.persistence.mapper.ParcelDomainMapper;
import com.courier.management.parcel.adapter.out.persistence.mapper.UserDomainMapper;
import com.courier.management.parcel.application.port.out.ParcelManagementWritePort;
import com.courier.management.parcel.domain.ParcelAddressDomain;
import com.courier.management.parcel.domain.ParcelDomain;
import com.courier.management.parcel.domain.RoleEnumDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
class ParcelManagementWriteAdapter implements ParcelManagementWritePort {

    private final ParcelDomainMapper parcelDomainMapper;
    private final UserDomainMapper userDomainMapper;
    private final AddressDomainMapper addressDomainMapper;
    private final ParcelRepository parcelRepository;
    private final UserRepository userRepository;

    @Override
    public void createParcel(ParcelDomain parcelDomain) {
        var sender = parcelDomain.getUsers().stream().filter(x -> x.getRole().equals(RoleEnumDomain.SENDER)).findFirst()
                .orElseThrow();
        var recipient = parcelDomain.getUsers().stream().filter(x -> x.getRole().equals(RoleEnumDomain.RECIPIENT))
                .findFirst().orElseThrow();

        UserEntity senderEntity = userRepository.findById(sender.getUser()).orElseThrow();
        UserEntity recipientEntity = userRepository.findById(recipient.getUser()).orElseThrow();

        ParcelAddressDomain senderAddress = parcelDomain.getAddress().stream()
                .filter(x -> x.getRole().equals(RoleEnumDomain.SENDER)).findFirst().orElseThrow();
        ParcelAddressDomain recipientAddress = parcelDomain.getAddress().stream()
                .filter(x -> x.getRole().equals(RoleEnumDomain.RECIPIENT)).findFirst().orElseThrow();

        AddressEntity senderAddressEntity = senderEntity.getAddress().stream()
                .filter(x -> x.getId().equals(senderAddress.getAddress().getId())).findFirst().orElseThrow();
        AddressEntity recipientAddressEntity = recipientEntity.getAddress().stream()
                .filter(x -> x.getId().equals(recipientAddress.getAddress().getId())).findFirst().orElseThrow();

        ParcelEntity parcelEntity = parcelDomainMapper.toParcelEntity(parcelDomain);
        parcelRepository.save(parcelEntity);

        parcelEntity.addAddress(senderAddressEntity, RoleEnum.SENDER);
        parcelEntity.addAddress(recipientAddressEntity, RoleEnum.RECIPIENT);

        senderEntity.addParcel(parcelEntity, RoleEnum.SENDER);
        recipientEntity.addParcel(parcelEntity, RoleEnum.RECIPIENT);
    }
}
