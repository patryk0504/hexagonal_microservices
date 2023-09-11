package com.courier.management.parcel.adapter.in.web;

import com.courier.management.parcel.adapter.in.web.model.ParcelDto;
import com.courier.management.parcel.application.port.in.CreateParcelUseCase;
import com.courier.management.parcel.application.port.in.GetParcelsUseCase;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CourierController.class)
@ContextConfiguration(classes = ParcelController.class)
class ParcelControllerTest {

    @MockBean
    private CreateParcelUseCase createParcelUseCase;
    @MockBean
    private GetParcelsUseCase getParcelsUseCase;

    @Autowired private MockMvc mvc;

    @Autowired private ObjectMapper objectMapper;

    @Test
    void createParcel() throws Exception {
        // given
        doNothing().when(createParcelUseCase).createParcel(any());
        // when
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/parcels")
                .content(asJsonString(new ParcelDto()))
                .contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());

        // then
    }

    @Test
    void getParcels() throws Exception {
        // given
        Page<ParcelDto> parcels = new PageImpl<>(List.of(new ParcelDto()));
        when(getParcelsUseCase.getParcels(anyString(), anyString(), anyInt(), anyInt())).thenReturn(
                parcels);
        // when
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/parcels").param("sortOrder", "ASC").param("sortBy", "id")
                        .param("page", String.valueOf(1)).param("pageSize", String.valueOf(1))
                        .contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").exists());
        // then
    }

    @Test
    void getParcelsUnsigned() throws Exception {
        // given
        when(getParcelsUseCase.getUnsignedParcels()).thenReturn(Set.of(new ParcelDto()));
        // when
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/parcels/unsigned")
                        .contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].id").exists());
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