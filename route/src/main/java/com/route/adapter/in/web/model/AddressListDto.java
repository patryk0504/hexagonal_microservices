package com.route.adapter.in.web.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class AddressListDto {
    List<AddressDto> addressList;
}
