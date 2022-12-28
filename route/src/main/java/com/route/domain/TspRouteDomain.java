package com.route.domain;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class TspRouteDomain {
    List<AddressDomain> route;
}
