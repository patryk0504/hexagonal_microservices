package com.route.application.service;

import com.route.adapter.in.web.model.model.ParcelDto;
import com.route.application.port.out.GetParcelsForCourierUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class ParcelManagementService implements GetParcelsForCourierUseCase {

    private static final String FILTERED_PARCELS_FOR_COURIER_URI = "/courier/%s/parcels/%s";
    private static final String PARCELS_FOR_COURIER_URI = "/courier/%s/parcels";
    private final WebClient webClient;

    @Override
    public Set<ParcelDto> getParcelsForCourier(long courierId) {
        Flux<ParcelDto> parcelDtoFlux = webClient.get().uri(String.format(PARCELS_FOR_COURIER_URI, courierId))
                .retrieve()
                .bodyToFlux(ParcelDto.class);
        return parcelDtoFlux.toStream().collect(Collectors.toSet());
    }

    @Override
    public Set<ParcelDto> getParcelsFilteredForCourier(long courierId, String status) {
        Flux<ParcelDto> parcelDtoFlux;

        try {
            parcelDtoFlux = webClient.get().uri(String.format(FILTERED_PARCELS_FOR_COURIER_URI, courierId, status))
                    .retrieve()
                    .bodyToFlux(ParcelDto.class);
            return parcelDtoFlux.toStream().collect(Collectors.toSet());
        } catch (WebClientResponseException e) {
            log.error("Error when calling: {} with arguments: {},{}", FILTERED_PARCELS_FOR_COURIER_URI, courierId,
                    status, e);
            throw e;
        }
    }
}
