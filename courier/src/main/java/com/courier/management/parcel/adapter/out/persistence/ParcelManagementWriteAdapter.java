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

    @Override
    public void createParcel(ParcelDomain parcelDomain) {
        parcelRepository.save(parcelDomainMapper.toParcelEntity(parcelDomain));
    }
}
