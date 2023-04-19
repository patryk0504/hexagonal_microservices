package com.courier.management.parcel.adapter.in.web.mapper;

import com.courier.management.parcel.adapter.in.web.model.UserDto;
import com.courier.management.parcel.domain.UserDomain;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = "spring")
public interface UserDtoMapper {
    UserDto toUserDto(UserDomain userDomain);

    UserDomain toUserDomain(UserDto userDto);

    List<UserDto> toUserDtoList(List<UserDomain> userDomainList);
}
