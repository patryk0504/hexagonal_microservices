package com.route.application.service.tsp;

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

//TODO in this algo start and end points not stay 
@Component
@Getter
@Setter
@RequiredArgsConstructor
public class TspNearestNeighbor implements TspAlgorithm {

    private final DomainMapper domainMapper;
    private final GoogleApi googleApi;

    // Wybór początkowego miasta i znalezienie najbliższego nieodwiedzonego miasta
    private AddressDomain chooseNextCity(AddressDomain currentCity, List<AddressDomain> unvisitedCities) throws Exception {
        double shortestDistance = Double.MAX_VALUE;
        AddressDomain closestCity = null;
        for (AddressDomain city : unvisitedCities) {
            double distance = googleApi.calculateTotalDistance(currentCity, city);
            if (distance < shortestDistance) {
                shortestDistance = distance;
                closestCity = city;
            }
        }
        return closestCity;
    }

    // Znalezienie najkrótszej trasy z wykorzystaniem algorytmu "najbliższego sąsiada"
    private TspRouteDomain findShortestRoute(List<AddressDomain> cities) throws Exception {
        List<AddressDomain> shortestRoute = new ArrayList<>();
        List<AddressDomain> unvisitedCities = new ArrayList<>(cities);
        AddressDomain startCity = unvisitedCities.get(0);
        shortestRoute.add(startCity);
        unvisitedCities.remove(startCity);
        while (!unvisitedCities.isEmpty()) {
            AddressDomain currentCity = shortestRoute.get(shortestRoute.size() - 1);
            AddressDomain nextCity = chooseNextCity(currentCity, unvisitedCities);
            shortestRoute.add(nextCity);
            unvisitedCities.remove(nextCity);
        }
        return TspRouteDomain.builder().route(shortestRoute).build();
    }

    @Override
    public TspRouteDomain calculateBestRoute(TspRouteDomain tspRouteDomain) throws Exception {
        return findShortestRoute(tspRouteDomain.getRoute());
    }
}
