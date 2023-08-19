package com.route.application.service;

import com.route.adapter.in.web.model.model.AddressRouteDto;
import com.route.adapter.in.web.model.model.ParcelDto;
import com.route.adapter.in.web.model.request.GeoStartAndEndPoints;
import com.route.application.port.in.GenerateRouteForCourierUseCase;
import com.route.application.port.in.GenerateRouteUseCase;
import com.route.application.port.out.GetParcelsForCourierUseCase;
import com.route.application.port.out.SaveParcelsRouteUseCase;
import com.route.application.service.tsp.TspAlgorithm;
import com.route.domain.TspRouteDomain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import reactor.core.publisher.Mono;

import java.util.Set;

@RequiredArgsConstructor
@Service
@Slf4j
public class RouteManagementService implements GenerateRouteUseCase, GenerateRouteForCourierUseCase {

    private final GetParcelsForCourierUseCase getParcelsForCourierUseCase;
    private final SaveParcelsRouteUseCase saveParcelsRouteUseCase;
    private final DomainMapper domainMapper;
    private final TspAlgorithm tspAlgorithm;

    @Override
    public TspRouteDomain generateRoute(AddressRouteDto addressRouteDto) {
        TspRouteDomain tspRouteDomain = domainMapper.toTspRouteDomain(addressRouteDto);
        try {
            return tspAlgorithm.calculateBestRoute(tspRouteDomain);
        } catch (Exception e) {
            //TODO add proper exception handling
            throw new RuntimeException("Cannot generateRoute");
        }
    }

    @Override
    public TspRouteDomain generateRoute(long courierId, GeoStartAndEndPoints geoStartAndEndPoints) {
        Set<ParcelDto> parcels = getParcelsForCourierUseCase.getParcelsForCourier(courierId);
        return generateAddressRouteResponse(geoStartAndEndPoints, parcels);
    }

    @Override
    public Mono<String> generateRouteForFilteredPackages(long courierId, GeoStartAndEndPoints geoStartAndEndPoints, String status) {
        Set<ParcelDto> parcels = getParcelsForCourierUseCase.getParcelsFilteredForCourier(courierId, status);
        log.info("Parcels from external service: {}", parcels);
        if (parcels.isEmpty()) {
            throw new NotFoundException("Not found any parcel with given status");
        }
        TspRouteDomain tspRouteDomain = generateAddressRouteResponse(geoStartAndEndPoints, parcels);
        return saveParcelsRouteUseCase.save(courierId, tspRouteDomain, geoStartAndEndPoints);
//        return tspRouteDomain;
    }

    private TspRouteDomain generateAddressRouteResponse(GeoStartAndEndPoints geoStartAndEndPoints, Set<ParcelDto> parcels) {
        TspRouteDomain tspRouteDomain = domainMapper.toTspRouteDomain(parcels, geoStartAndEndPoints);
        log.info("Mapped to TspRouteDomain: {}", tspRouteDomain);
        if (tspRouteDomain.getRoute().size() <= 3) {
            return tspRouteDomain;
        }

        try {
            return tspAlgorithm.calculateBestRoute(tspRouteDomain);
        } catch (Exception e) {
            //TODO add proper exception handling
            throw new RuntimeException("Cannot generateRoute");
        }
    }
}
