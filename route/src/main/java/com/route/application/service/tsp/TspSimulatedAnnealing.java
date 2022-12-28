package com.route.application.service.tsp;

import com.route.adapter.in.web.model.AddressListDto;
import com.route.application.service.DomainMapper;
import com.route.application.service.google.api.GoogleApi;
import com.route.domain.AddressDomain;
import com.route.domain.TspRouteDomain;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@Getter
@Setter
@RequiredArgsConstructor
public class TspSimulatedAnnealing implements TspSimulatedAnnealingAlgorithm {

    private double routeLength = 0;
    private final GoogleApi googleApi;
    private final DomainMapper domainMapper;


    // Obliczenie długości trasy z wykorzystaniem API Google Maps
    private void computeRouteLength(List<AddressDomain> route) throws Exception {
        routeLength = 0;
        for (int i = 0; i < route.size() - 1; i++) {
            AddressDomain city1 = route.get(i);
            AddressDomain city2 = route.get(i + 1);
            double distance = googleApi.calculateTotalDistance(city1, city2);
            routeLength += distance;
//            routeLength += directionsRoute.legs[0].distance.inMeters / 1000;
        }
    }

    // Losowe przemieszczenie miast w trasie
    private void randomSwap(List<AddressDomain> route) {
        Random r = new Random();
        int a = r.nextInt(route.size());
        int b = r.nextInt(route.size());
        AddressDomain cityA = route.get(a);
        AddressDomain cityB = route.get(b);
        route.set(a, cityB);
        route.set(b, cityA);
    }

    // Próba znalezienia lepszej trasy przez losowe przemieszczanie miast
    private TspRouteDomain anneal(List<AddressDomain> route) throws Exception {
        List<AddressDomain> generatedRoute = new ArrayList<>(route);
        computeRouteLength(generatedRoute);
        double temperature = 10000;
        double coolingRate = 0.003;
        while (temperature > 1) {
            randomSwap(generatedRoute);
            double currentLength = getRouteLength();
            double delta = currentLength - routeLength;
            if (delta > 0) {
                routeLength = currentLength;
            } else {
                double probability = Math.exp(delta / temperature);
                Random r = new Random();
                if (r.nextDouble() < probability) {
                    routeLength = currentLength;
                } else {
                    randomSwap(generatedRoute);
                }
            }
            temperature *= 1 - coolingRate;
        }

        return TspRouteDomain.builder().route(generatedRoute).build();
    }

    @Override
    public TspRouteDomain calculateBestRoute(AddressListDto addressListDto) throws Exception {
        return anneal(domainMapper.toTspRouteDomain(addressListDto).getRoute());
    }
}