package com.route.application.service.tsp;

import com.route.domain.TspRouteDomain;

public interface TspSimulatedAnnealingAlgorithm {
    TspRouteDomain calculateBestRoute(TspRouteDomain tspRouteDomain) throws Exception;
}
