package com.route.application.port.in;

import com.route.adapter.in.web.model.request.AddressListRequest;
import com.route.adapter.in.web.model.response.AddressRouteResponse;

public interface GenerateRouteUseCase {
    AddressRouteResponse generateRoute(AddressListRequest addressListRequest);
}
