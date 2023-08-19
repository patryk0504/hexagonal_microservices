package com.route.application.port.in;

import com.route.adapter.in.web.model.request.GeoStartAndEndPoints;
import com.route.domain.TspRouteDomain;
import reactor.core.publisher.Mono;

public interface GenerateRouteForCourierUseCase {
    TspRouteDomain generateRoute(long courierId, GeoStartAndEndPoints geoStartAndEndPoints);

    Mono<String> generateRouteForFilteredPackages(long courierId, GeoStartAndEndPoints geoStartAndEndPoints,
                                                  String status);

}
