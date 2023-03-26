package com.route.application.service.tsp;

import com.route.application.service.DomainMapper;
import com.route.application.service.google.api.RouteLengthCalculator;
import com.route.domain.AddressDomain;
import com.route.domain.TspRouteDomain;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@Getter
@Setter
@RequiredArgsConstructor
@Primary
public class TspSimulatedAnnealing implements TspAlgorithm {

    private final DomainMapper domainMapper;
    private final RouteLengthCalculator routeLengthCalculator;

    // Losowe przemieszczenie miast w trasie
    private void randomSwap(List<AddressDomain> route) {
        Random r = new Random();
        int a = r.nextInt(route.size() - 2) + 1; // exclude start and end points
        int b = r.nextInt(route.size() - 2) + 1; // exclude start and end points
        AddressDomain cityA = route.get(a);
        AddressDomain cityB = route.get(b);
        route.set(a, cityB);
        route.set(b, cityA);
    }

    // Próba znalezienia lepszej trasy przez losowe przemieszczanie miast
    private TspRouteDomain anneal(List<AddressDomain> route) throws Exception {
        List<AddressDomain> generatedRoute = new ArrayList<>(route);
        double routeLength = routeLengthCalculator.computeRouteLength(generatedRoute);
        double temperature = 10000;
        double coolingRate = 0.003;
        while (temperature > 1) {
            randomSwap(generatedRoute);
            double currentLength = routeLength;
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
    public TspRouteDomain calculateBestRoute(TspRouteDomain tspRouteDomain) throws Exception {
        return anneal(tspRouteDomain.getRoute());
    }
}