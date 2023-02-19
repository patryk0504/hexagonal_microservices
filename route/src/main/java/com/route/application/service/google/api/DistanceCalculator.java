package com.route.application.service.google.api;

import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.errors.NotFoundException;
import com.google.maps.errors.ZeroResultsException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.route.domain.AddressDomain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DistanceCalculator implements GoogleApi {

    private final GeoApiContext context;

    private static double getDirectionSum(DirectionsResult distanceViaGeo) {
        return Arrays.stream(distanceViaGeo.routes).mapToDouble(r -> r.legs[0].distance.inMeters).sum();
    }

    @Override
    public double calculateTotalDistance(AddressDomain source, AddressDomain destination) throws IOException, InterruptedException, ApiException {
        LatLng sourceLatLng = new LatLng(source.getLatitude(), source.getLongitude());
        LatLng destinationLatLng = new LatLng(destination.getLatitude(), destination.getLongitude());
        Optional<DirectionsResult> distanceViaGeo = getDistanceBetweenLocalizations(sourceLatLng, destinationLatLng);
        if (distanceViaGeo.isPresent()) {
            return getDirectionSum(distanceViaGeo.get());
        } else {
            Optional<DirectionsResult> distanceViaNatural = calculateTotalDistanceViaNaturalAddress(
                    source.getAddress(),
                    destination.getAddress());
            DirectionsResult distanceViaNaturalResult = distanceViaNatural.orElseThrow(() -> new NotFoundException(
                    String.format("Cannot find distance between given address: %s - %s", source, destination)));

            return getDirectionSum(distanceViaNaturalResult);
        }
    }

    public Optional<DirectionsResult> calculateTotalDistanceViaNaturalAddress(String sourceAddress, String destinationAddress) {
        try {
            GeocodingResult[] sourceResults = GeocodingApi.geocode(context, sourceAddress).await();
            GeocodingResult[] destinationResults = GeocodingApi.geocode(context, destinationAddress).await();

            if (sourceResults.length == 0) {
                log.warn("Cannot find given source address: {}", sourceAddress);
                return Optional.empty();
            }

            if (destinationResults.length == 0) {
                log.warn("Cannot find given destination address: {}", destinationAddress);
                return Optional.empty();
            }

            LatLng sourceLatLng = sourceResults[0].geometry.location;
            LatLng destinationLatLng = destinationResults[0].geometry.location;

            return getDistanceBetweenLocalizations(sourceLatLng, destinationLatLng);
        } catch (Exception e) {
            log.error("Error while calculating distance between addresses", e);
            return Optional.empty();
        }
    }

    private Optional<DirectionsResult> getDistanceBetweenLocalizations(LatLng source, LatLng destination) throws ApiException, InterruptedException, IOException {
        DirectionsResult directionsResult = null;
        try {
            directionsResult = DirectionsApi.getDirections(context, source.toString(), destination.toString())
                    .await();
        } catch (NotFoundException e) {
            log.error("Cannot find given address", e);
        } catch (ZeroResultsException e) {
            log.error("Cannot find route for given addresses {} and {}", source, destination, e);
        }
        return Optional.ofNullable(directionsResult);
    }
}

