package com.route.domain;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class TspRouteDomain {
    @Builder.Default
    List<AddressDomain> route = new ArrayList<>();
}
