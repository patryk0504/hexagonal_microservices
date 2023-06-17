package com.courier.management.parcel.adapter.out.persistence.mapper;

import com.courier.management.parcel.adapter.out.persistence.entity.ParcelAddressEntity;
import com.courier.management.parcel.adapter.out.persistence.entity.UserEntity;
import com.courier.management.parcel.adapter.out.persistence.entity.UserParcelEntity;
import com.courier.management.parcel.adapter.out.persistence.entity.UserParcelId;
import com.courier.management.parcel.domain.ParcelAddressDomain;
import com.courier.management.parcel.domain.ParcelDomain;
import com.courier.management.parcel.domain.UserDomain;
import com.courier.management.parcel.domain.UserParcelDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = "spring")
public interface UserDomainMapper {
    default Long map(UserEntity user) {
        return user.getId();
    }

    default Long map(UserParcelId userParcelId) {
        return userParcelId.getParcelId();
    }

    @Mapping(target = "name", source = "parcel.name")
    @Mapping(target = "address", source = "parcel.address")
    @Mapping(target = "users", source = "parcel.users")
    @Mapping(target = "weight", source = "parcel.weight")
    @Mapping(target = "dimensions", source = "parcel.dimensions")
    @Mapping(target = "status", source = "parcel.status")
    ParcelDomain toParcelDomain(UserParcelEntity userParcel);

    UserParcelDomain toUserParcelDomain(UserParcelEntity userParcelEntity);

    ParcelAddressDomain toParcelAddressDomain(ParcelAddressEntity parcelAddress);

    UserDomain toUserDomain(UserEntity user);

    @Mapping(target = "parcels", ignore = true)
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "id", ignore = true)
    UserEntity toUserEntity(UserDomain userDomain);

    List<UserDomain> toUserDomains(List<UserEntity> userEntityList);
}
