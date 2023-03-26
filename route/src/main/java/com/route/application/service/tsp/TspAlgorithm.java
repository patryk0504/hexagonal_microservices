package com.route.application.service.tsp;

import com.route.domain.TspRouteDomain;

public interface TspAlgorithm {
    TspRouteDomain calculateBestRoute(TspRouteDomain tspRouteDomain) throws Exception;
}
