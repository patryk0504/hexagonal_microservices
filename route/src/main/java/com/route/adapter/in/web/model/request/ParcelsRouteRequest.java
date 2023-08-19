package com.route.adapter.in.web.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ParcelsRouteRequest {
    @NotNull
    private GeoStartAndEndPoints startAndEndPoints;
    private String status;
}
