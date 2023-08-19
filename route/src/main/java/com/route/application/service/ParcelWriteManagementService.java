package com.route.application.service;

import com.route.adapter.in.web.model.model.DeliveryCreateRequest;
import com.route.adapter.in.web.model.request.GeoStartAndEndPoints;
import com.route.application.port.out.SaveParcelsRouteUseCase;
import com.route.domain.TspRouteDomain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
@Slf4j
public class ParcelWriteManagementService implements SaveParcelsRouteUseCase {

    private static final String SAVE_PARCELS_ROUTE = "/deliveries/courier/%d";
    private final WebClient webClient;
    private final DomainMapper domainMapper;

    @Override
    public Mono<String> save(long courierId, TspRouteDomain tspRouteDomain, GeoStartAndEndPoints geoStartAndEndPoints) {
        DeliveryCreateRequest deliveryCreateRequest = domainMapper.toDeliveryCreateRequest(tspRouteDomain,
                geoStartAndEndPoints);
        log.info("Delivery create request: {}", deliveryCreateRequest);

        try {
            return webClient.post()
                    .uri("/deliveries/courier/" + courierId)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .body(Mono.just(deliveryCreateRequest), DeliveryCreateRequest.class)
                    .retrieve()
                    .bodyToMono(String.class);
        } catch (WebClientException e) {
            log.error("Error when calling: {} with arguments: {}", SAVE_PARCELS_ROUTE, courierId, e);
            throw e;
        }
    }
}
