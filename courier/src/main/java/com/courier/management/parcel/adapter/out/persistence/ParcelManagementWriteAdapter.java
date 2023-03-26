package com.courier.management.parcel.adapter.out.persistence;

import com.courier.management.parcel.application.port.out.ParcelManagementWritePort;
import com.courier.management.parcel.domain.ParcelDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class ParcelManagementWriteAdapter implements ParcelManagementWritePort {

    private final ParcelDomainMapper parcelDomainMapper;
    private final ParcelRepository parcelRepository;
    private final AddressRepository addressRepository;

    @Override
    public void createParcel(ParcelDomain parcelDomain) {
        ParcelEntity parcelEntity = parcelDomainMapper.toParcelEntity(parcelDomain);
        //TODO add proper address and users
//        AddressEntity addressEntity = addressRepository.finB
//        parcelEntity.addAddress();
        parcelRepository.save(parcelDomainMapper.toParcelEntity(parcelDomain));
    }
}
