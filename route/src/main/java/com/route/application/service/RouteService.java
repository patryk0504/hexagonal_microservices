package com.route.application.service;

import com.route.adapter.in.web.model.AddressListDto;
import com.route.adapter.in.web.model.mapper.DtoMapper;
import com.route.adapter.in.web.model.request.AddressListRequest;
import com.route.adapter.in.web.model.response.AddressRouteResponse;
import com.route.application.port.in.GenerateRouteUseCase;
import com.route.application.service.tsp.TspSimulatedAnnealingAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RouteService implements GenerateRouteUseCase {

    private final DomainMapper domainMapper;
    private final DtoMapper dtoMapper;
    private final TspSimulatedAnnealingAlgorithm tspAlgorithm;

    @Override
    public AddressRouteResponse generateRoute(AddressListRequest addressListRequest) {
        AddressListDto addressListDto = domainMapper.toCityListDto(addressListRequest);
        try {
            return dtoMapper.toAddressRouteResponse(tspAlgorithm.calculateBestRoute(addressListDto));
        } catch (Exception e) {
            //TODO add proper exception handling
            throw new RuntimeException("Cannot generateRoute");
        }
    }
}
