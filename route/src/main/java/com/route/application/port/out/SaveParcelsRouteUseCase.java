package com.route.application.port.out;

import com.route.adapter.in.web.model.request.GeoStartAndEndPoints;
import com.route.domain.TspRouteDomain;
import reactor.core.publisher.Mono;

public interface SaveParcelsRouteUseCase {
    Mono<String> save(long courierId, TspRouteDomain tspRouteDomain, GeoStartAndEndPoints geoStartAndEndPoints);
}
