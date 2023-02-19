package com.route.application.port.in;

import com.route.adapter.in.web.model.model.AddressRouteDto;

public interface GenerateRouteUseCase {
    AddressRouteDto generateRoute(AddressRouteDto addressListRequest);
}
