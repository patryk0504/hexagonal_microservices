package com.route.application.service;

import com.route.adapter.in.web.model.mapper.DtoMapper;
import com.route.adapter.in.web.model.model.AddressRouteDto;
import com.route.adapter.in.web.model.model.ParcelDto;
import com.route.adapter.in.web.model.request.GeoStartAndEndPoints;
import com.route.application.port.in.GenerateRouteForCourierUseCase;
import com.route.application.port.in.GenerateRouteUseCase;
import com.route.application.port.out.GetParcelsForCourierUseCase;
import com.route.application.service.tsp.TspSimulatedAnnealingAlgorithm;
import com.route.domain.TspRouteDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class RouteManagementService implements GenerateRouteUseCase, GenerateRouteForCourierUseCase {

    private final GetParcelsForCourierUseCase getParcelsForCourierUseCase;
    private final DomainMapper domainMapper;
    private final DtoMapper dtoMapper;
    private final TspSimulatedAnnealingAlgorithm tspAlgorithm;

    @Override
    public AddressRouteDto generateRoute(AddressRouteDto addressRouteDto) {
        TspRouteDomain tspRouteDomain = domainMapper.toTspRouteDomain(addressRouteDto);
        try {
            return dtoMapper.toAddressRouteResponse(tspAlgorithm.calculateBestRoute(tspRouteDomain));
        } catch (Exception e) {
            //TODO add proper exception handling
            throw new RuntimeException("Cannot generateRoute");
        }
    }

    @Override
    public AddressRouteDto generateRoute(long courierId, GeoStartAndEndPoints geoStartAndEndPoints) {
        Set<ParcelDto> parcels = getParcelsForCourierUseCase.getParcelsForCourier(courierId);
        return generateAddressRouteResponse(geoStartAndEndPoints, parcels);
    }

    @Override
    public AddressRouteDto generateRouteForFilteredPackages(long courierId, GeoStartAndEndPoints geoStartAndEndPoints, String status) {
        Set<ParcelDto> parcels = getParcelsForCourierUseCase.getParcelsFilteredForCourier(courierId, status);
        return generateAddressRouteResponse(geoStartAndEndPoints, parcels);
    }

    private AddressRouteDto generateAddressRouteResponse(GeoStartAndEndPoints geoStartAndEndPoints, Set<ParcelDto> parcels) {
        TspRouteDomain tspRouteDomain = domainMapper.toTspRouteDomain(parcels, geoStartAndEndPoints);

        if (tspRouteDomain.getRoute().size() <= 3) {
            return dtoMapper.toAddressRouteResponse(tspRouteDomain);
        }

        try {
            return dtoMapper.toAddressRouteResponse(tspAlgorithm.calculateBestRoute(tspRouteDomain));
        } catch (Exception e) {
            //TODO add proper exception handling
            throw new RuntimeException("Cannot generateRoute");
        }
    }
}
