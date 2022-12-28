package com.route.application.service.google.api;

import com.google.maps.errors.ApiException;
import com.route.domain.AddressDomain;

import java.io.IOException;

public interface GoogleApi {
    double calculateTotalDistance(AddressDomain source, AddressDomain destination) throws IOException, InterruptedException, ApiException;
}
