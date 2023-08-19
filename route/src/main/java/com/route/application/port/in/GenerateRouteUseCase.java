package com.route.application.port.in;

import com.route.adapter.in.web.model.model.AddressRouteDto;
import com.route.domain.TspRouteDomain;

public interface GenerateRouteUseCase {
    TspRouteDomain generateRoute(AddressRouteDto addressListRequest);
}
