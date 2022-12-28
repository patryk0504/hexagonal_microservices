package com.route.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class AddressDomain {
    double latitude;
    double longitude;
}
