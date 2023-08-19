package com.route.adapter.in.web;

import com.route.adapter.in.web.model.mapper.DtoMapper;
import com.route.adapter.in.web.model.model.AddressRouteDto;
import com.route.adapter.in.web.model.request.GeoStartAndEndPoints;
import com.route.adapter.in.web.model.request.ParcelsRouteRequest;
import com.route.application.port.in.GenerateRouteForCourierUseCase;
import com.route.application.port.in.GenerateRouteUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("api/v1/route")
@RequiredArgsConstructor
@Validated
public class RouteController {
    private final GenerateRouteUseCase generateRouteUseCase;
    private final GenerateRouteForCourierUseCase generateRouteForCourierUseCase;
    private final DtoMapper mapper;

    @PostMapping(path = "")
    AddressRouteDto generateRoute(@RequestBody AddressRouteDto cityDomainList) {
        return mapper.toAddressRouteResponse(generateRouteUseCase.generateRoute(cityDomainList));
    }

    @GetMapping(path = "/courier/{courier_id}")
    AddressRouteDto generateRouteForCourier(@PathVariable("courier_id") long courierId, @RequestBody @Valid GeoStartAndEndPoints geoStartAndEndPoints) {
        return mapper.toAddressRouteResponse(
                generateRouteForCourierUseCase.generateRoute(courierId, geoStartAndEndPoints));
    }

    //TODO generated route for courier should be saved into route_table via api
    //should generated route be also saved as new delivery in delivery table?
    @PostMapping(path = "/courier/{courier_id}")
    Mono<String> generateRouteForFilteredPackages(@PathVariable("courier_id") long courierId, @RequestBody @Valid ParcelsRouteRequest parcelsRouteRequest) {
//        return mapper.toAddressRouteResponse(generateRouteForCourierUseCase.generateRouteForFilteredPackages(courierId, parcelsRouteRequest.getStartAndEndPoints(), parcelsRouteRequest.getStatus()));
        return generateRouteForCourierUseCase.generateRouteForFilteredPackages(courierId,
                parcelsRouteRequest.getStartAndEndPoints(), parcelsRouteRequest.getStatus());
    }

    //TODO generate route for delivery in delivery_table and save new route
}
