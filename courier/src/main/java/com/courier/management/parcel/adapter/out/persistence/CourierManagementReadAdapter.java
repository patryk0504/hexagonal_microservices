package com.courier.management.parcel.adapter.out.persistence;

import com.courier.management.parcel.application.port.out.CourierManagementReadPort;
import com.courier.management.parcel.domain.CourierDomain;
import com.courier.management.parcel.domain.ParcelDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class CourierManagementReadAdapter implements CourierManagementReadPort {

    private final CourierRepository courierRepository;
    private final CourierDomainMapper courierDomainMapper;
    private final ParcelDomainMapper parcelDomainMapper;

    @Override
    public Set<ParcelDomain> getParcelsForCourier(long courierId) {
        ParcelsForCourierProjection parcels = courierRepository.findAllParcelsById(courierId);
        if (parcels == null) {
            return Set.of();
        }
        return parcelDomainMapper.toParcelDomainSet(parcels.getParcels());
    }

    @Override
    public Page<CourierDomain> getCouriers(String sortOrder, String sortBy, int page, int pageSize) {
        Sort.Direction direction = sortOrder != null && sortOrder.equalsIgnoreCase("ASC") ?
                Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy != null ? sortBy : "id");
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        return courierDomainMapper.toCourierDomainPage(courierRepository.findAll(pageable));
    }
}
