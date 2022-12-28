package com.route.adapter.in.web;

import com.route.adapter.in.web.model.request.AddressListRequest;
import com.route.adapter.in.web.model.response.AddressRouteResponse;
import com.route.application.port.in.GenerateRouteUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/route")
@RequiredArgsConstructor
public class RouteController {
    private final GenerateRouteUseCase generateRouteUseCase;

    @GetMapping(path = "")
    AddressRouteResponse generateRoute(@RequestBody AddressListRequest cityDomainList) {
        return generateRouteUseCase.generateRoute(cityDomainList);
    }
}
