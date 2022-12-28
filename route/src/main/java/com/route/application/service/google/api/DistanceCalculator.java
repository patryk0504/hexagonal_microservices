package com.route.application.service.google.api;

import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.LatLng;
import com.route.domain.AddressDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class DistanceCalculator implements GoogleApi {

    private final GeoApiContext context;

    @Override
    public double calculateTotalDistance(AddressDomain source, AddressDomain destination) throws IOException, InterruptedException, ApiException {
        LatLng latLng1 = new LatLng(source.getLatitude(), source.getLongitude());
        LatLng latLng2 = new LatLng(destination.getLatitude(), destination.getLongitude());
        DirectionsResult directionsResult = DirectionsApi.getDirections(context, latLng1.toString(), latLng2.toString())
                .await();
        return Arrays.stream(directionsResult.routes).mapToDouble(route -> route.legs[0].distance.inMeters).sum();
    }
}
