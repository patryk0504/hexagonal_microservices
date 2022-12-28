package com.route.application.service.tsp;

import com.route.adapter.in.web.model.AddressListDto;
import com.route.domain.TspRouteDomain;

public interface TspSimulatedAnnealingAlgorithm {
    TspRouteDomain calculateBestRoute(AddressListDto addressListDto) throws Exception;
}
