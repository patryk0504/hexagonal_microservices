package com.route.adapter.in.web.model.request;

import com.route.adapter.in.web.model.model.GeoAddressDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class GeoStartAndEndPoints {
    @NotBlank(message = "Start point cannot be null")
    GeoAddressDto startPoint;
    @NotBlank(message = "End point cannot be null")
    GeoAddressDto endPoint;
}
