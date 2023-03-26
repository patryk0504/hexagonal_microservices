package com.route.application.service.google.api;

import com.route.domain.AddressDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RouteLengthCalculator {
    private final GoogleApi googleApi;

    // Obliczenie długości trasy z wykorzystaniem API Google Maps
    public double computeRouteLength(List<AddressDomain> route) throws Exception {
        double routeLength = 0;
        for (int i = 0; i < route.size() - 1; i++) {
            AddressDomain city1 = route.get(i);
            AddressDomain city2 = route.get(i + 1);
            double distance = googleApi.calculateTotalDistance(city1, city2);
            routeLength += distance;
        }
        return routeLength;
    }
}
