package com.courier.management.parcel.adapter.out.persistence;

import com.courier.management.parcel.adapter.out.persistence.entity.CourierEntity;
import com.courier.management.parcel.adapter.out.persistence.mapper.CourierDomainMapper;
import com.courier.management.parcel.application.port.out.CourierManagementReadPort;
import com.courier.management.parcel.domain.CourierDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CourierManagementReadAdapter implements CourierManagementReadPort {

    private final CourierRepository courierRepository;
    private final CourierDomainMapper courierDomainMapper;

    @Override
    public Page<CourierDomain> getCouriers(String sortOrder, String sortBy, int page, int pageSize) {
        Sort.Direction direction = sortOrder != null && sortOrder.equalsIgnoreCase("ASC") ?
                Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy != null ? sortBy : "id");
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        return courierDomainMapper.toCourierDomainPage(courierRepository.findAll(pageable));
    }

    @Override
    public Optional<CourierDomain> getCourier(long courierId) {
        Optional<CourierEntity> courier = courierRepository.findById(courierId);
        return courier.map(courierDomainMapper::toCourierDomain);
    }
}
