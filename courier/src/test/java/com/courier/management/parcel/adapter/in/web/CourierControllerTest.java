package com.courier.management.parcel.adapter.in.web;

import com.courier.management.parcel.adapter.in.web.model.AssignParcelDto;
import com.courier.management.parcel.adapter.in.web.model.CourierDto;
import com.courier.management.parcel.adapter.in.web.model.ParcelDto;
import com.courier.management.parcel.application.port.in.AssignParcelUseCase;
import com.courier.management.parcel.application.port.in.CourierCrudOperations;
import com.courier.management.parcel.application.port.in.GetParcelForCourierUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CourierController.class)
@ContextConfiguration(classes = CourierController.class)
class CourierControllerTest {

    @MockBean private CourierCrudOperations courierCrudOperations;

    @MockBean private GetParcelForCourierUseCase getParcelForCourierUseCase;

    @MockBean private AssignParcelUseCase assignParcelUseCase;

    @Autowired private MockMvc mvc;

    @Autowired private ObjectMapper objectMapper;


    @Test
    void createCourier() throws Exception {
        // given
        doNothing().when(courierCrudOperations).createCourier(any());
        // when
        // then
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/couriers").content(asJsonString(new CourierDto()))
                .contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getParcelsForCourier() throws Exception {
        // given
        Set<ParcelDto> parcelDtoSet = Set.of(new ParcelDto());
        when(getParcelForCourierUseCase.getParcelsForCourierByStatus(anyLong(), anyString())).thenReturn(parcelDtoSet);
        // when
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/couriers/1/parcels/created")
                        .contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].name").exists());
        // then
    }

    @Test
    void getAllParcelsForCourier() throws Exception {
        // given
        Set<ParcelDto> parcelDtoSet = Set.of(new ParcelDto());
        when(getParcelForCourierUseCase.getParcelsForCourier(anyLong())).thenReturn(parcelDtoSet);
        // when
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/couriers/1/parcels").contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$[*].id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].name").exists());
        // then
    }

    @Test
    void getCouriers() throws Exception {
        // given
        Page<CourierDto> courierDtoPage = new PageImpl<>(List.of(new CourierDto()));
        when(courierCrudOperations.getCouriers(anyString(), anyString(), anyInt(), anyInt())).thenReturn(
                courierDtoPage);
        // when
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/couriers").param("sortOrder", "ASC").param("sortBy", "id")
                        .param("page", String.valueOf(1)).param("pageSize", String.valueOf(1))
                        .contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").exists());
        // then
    }

    @Test
    void assignParcel() throws Exception {
        // given
        doNothing().when(assignParcelUseCase).assignParcelToCourier(anyLong(), anyList());
        // when
        // then
        mvc.perform(MockMvcRequestBuilders.put("/api/v1/couriers/1/parcel").content(asJsonString(new AssignParcelDto()))
                .contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isNoContent());
    }

    @Test
    void getCourier() throws Exception {
        // given
        when(courierCrudOperations.getCourier(anyLong())).thenReturn(new CourierDto());
        // when
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/couriers/1").contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());
        // then
    }

    private String asJsonString(final Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}