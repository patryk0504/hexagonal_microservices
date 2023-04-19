package com.courier.management.parcel.adapter.in.web.model;

import lombok.Data;

import java.util.List;

@Data
public class AssignParcelDto {
    List<Long> selectedParcelIds;
}
