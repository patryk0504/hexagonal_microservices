package com.courier.management.parcel.adapter.in.web;

import com.courier.management.parcel.adapter.in.web.model.AddressDto;
import com.courier.management.parcel.adapter.in.web.model.UserDto;
import com.courier.management.parcel.application.port.in.UserAddressCrudOperations;
import com.courier.management.parcel.application.port.in.UserCrudOperations;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CourierController.class)
@ContextConfiguration(classes = UserController.class)
class UserControllerTest {

    @MockBean
    private UserCrudOperations userCrudOperations;
    @MockBean
    private UserAddressCrudOperations userAddressCrudOperations;

    @Autowired private MockMvc mvc;

    @Autowired private ObjectMapper objectMapper;

    @Test
    void getUsers() throws Exception {
        // given
        when(userCrudOperations.getUsers()).thenReturn(List.of(new UserDto()));
        // when
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].id").exists());

        // then
    }

    @Test
    void addUser() throws Exception {
        // given
        doNothing().when(userCrudOperations).addUser(any());
        // when
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/users").content(asJsonString(new UserDto()))
                .contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
        // then
    }

    @Test
    void getUserAddresses() throws Exception {
        // given
        when(userAddressCrudOperations.getUserAddresses(anyLong())).thenReturn(List.of(new AddressDto()));
        // when
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/users/1/address")
                        .contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].id").exists());
        // then
    }

    @Test
    void addUserAddress() throws Exception {
        // given
        doNothing().when(userAddressCrudOperations).addUserAddress(anyLong(), any());
        // when
        mvc.perform(MockMvcRequestBuilders.post("/api/v1/users/1/address").content(asJsonString(new AddressDto()))
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