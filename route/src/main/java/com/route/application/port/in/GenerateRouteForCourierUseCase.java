package com.route.application.port.in;

import com.route.adapter.in.web.model.model.AddressRouteDto;
import com.route.adapter.in.web.model.request.GeoStartAndEndPoints;

public interface GenerateRouteForCourierUseCase {
    AddressRouteDto generateRoute(long courierId, GeoStartAndEndPoints geoStartAndEndPoints);

    AddressRouteDto generateRouteForFilteredPackages(long courierId, GeoStartAndEndPoints geoStartAndEndPoints, String status);

}
