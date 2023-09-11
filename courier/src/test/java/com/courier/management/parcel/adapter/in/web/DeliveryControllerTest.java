package com.courier.management.parcel.adapter.in.web;

import com.courier.management.parcel.adapter.in.web.model.DeliveryCreateRequest;
import com.courier.management.parcel.adapter.in.web.model.DeliveryDto;
import com.courier.management.parcel.application.port.in.CreateDeliveryUseCase;
import com.courier.management.parcel.application.port.in.GetDeliveriesUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CourierController.class)
@ContextConfiguration(classes = DeliveryController.class)
class DeliveryControllerTest {

    @MockBean
    private CreateDeliveryUseCase createDeliveryUseCase;

    @MockBean
    private GetDeliveriesUseCase getDeliveriesUseCase;

    @Autowired private MockMvc mvc;

    @Autowired private ObjectMapper objectMapper;

    @Test
    void createDelivery() throws Exception {
        // given
        when(createDeliveryUseCase.createDelivery(anyLong(), any())).thenReturn(new DeliveryDto());
        // when
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/deliveries/courier/1")
                .content(asJsonString(new DeliveryCreateRequest()))
                .contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());

        // then
    }

    @Test
    void getCourierDeliveries() throws Exception {
        // given
        DeliveryDto deliveryDto = new DeliveryDto();
        deliveryDto.setId(1L);
        when(getDeliveriesUseCase.getCourierDeliveries(anyLong())).thenReturn(new DeliveryDto());
        // when
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/deliveries/courier/1")
                .contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());

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