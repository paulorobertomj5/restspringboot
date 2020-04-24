package com.springboot.restspringbootjpa.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.restspringbootjpa.data.vo.OrderVO;
import com.springboot.restspringbootjpa.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        OrderController.class
})
public class OrderControllerTest {

    @Autowired
    private OrderController controller;

    @MockBean
    private OrderService orderService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }


    @Test
    public void testFindByAll_Success() throws Exception {

        List<OrderVO> listResponse = new ArrayList<>();
        OrderVO response = new OrderVO();
        response.setKey(1L);
        response.setClientId(1);
        response.setProductId(1);
        response.setPrice(100D);
        response.setQtd(10);

        listResponse.add(response);

        when(orderService.findByAll()).thenReturn(listResponse);


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/order/")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expectedKey = "1";
        String expectedClientId = "1";
        String expectedProductId = "1";
        String expectedPrice = "100.0";
        String expectedQtd = "10";


        assertNotNull(result.getResponse());

        String jsonResult = result.getResponse().getContentAsString();
        assertTrue(jsonResult.contains(expectedKey));
        assertTrue(jsonResult.contains(expectedClientId));
        assertTrue(jsonResult.contains(expectedProductId));
        assertTrue(jsonResult.contains(expectedQtd));
    }

    @Test
    public void testFindByAll_Error() throws Exception {

        List<OrderVO> listResponse = new ArrayList<>();
        OrderVO response = new OrderVO();
        response.setKey(1L);
        response.setClientId(1);
        response.setProductId(1);
        response.setPrice(100D);
        response.setQtd(10);

        listResponse.add(response);

        when(orderService.findByAll()).thenReturn(listResponse);


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/order/")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expectedKey = "2";
        String expectedClientId = "2";
        String expectedProductId = "2";
        String expectedPrice = "200.0";
        String expectedQtd = "20";


        assertNotNull(result.getResponse());

        String jsonResult = result.getResponse().getContentAsString();
        assertFalse(jsonResult.contains(expectedKey));
        assertFalse(jsonResult.contains(expectedClientId));
        assertFalse(jsonResult.contains(expectedProductId));
        assertFalse(jsonResult.contains(expectedPrice));
        assertFalse(jsonResult.contains(expectedQtd));
    }

    @Test
    public void testFindById_Success() throws Exception {

        OrderVO response = new OrderVO();
        response.setKey(1L);
        response.setClientId(1);
        response.setProductId(1);
        response.setPrice(100D);
        response.setQtd(10);


        when(orderService.findById(any())).thenReturn(response);


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/order/1")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expectedKey = "1";
        String expectedClientId = "1";
        String expectedProductId = "1";
        String expectedPrice = "100.0";
        String expectedQtd = "10";


        assertNotNull(result.getResponse());

        String jsonResult = result.getResponse().getContentAsString();
        assertTrue(jsonResult.contains(expectedKey));
        assertTrue(jsonResult.contains(expectedClientId));
        assertTrue(jsonResult.contains(expectedProductId));
        assertTrue(jsonResult.contains(expectedQtd));
    }

    @Test
    public void testFindByid_Error() throws Exception {

        OrderVO response = new OrderVO();
        response.setKey(1L);
        response.setClientId(1);
        response.setProductId(1);
        response.setPrice(100D);
        response.setQtd(10);


        when(orderService.findById(any())).thenReturn(response);


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/order/1")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expectedKey = "2";
        String expectedClientId = "2";
        String expectedProductId = "2";
        String expectedPrice = "200.0";
        String expectedQtd = "20";


        assertNotNull(result.getResponse());

        String jsonResult = result.getResponse().getContentAsString();
        assertFalse(jsonResult.contains(expectedKey));
        assertFalse(jsonResult.contains(expectedClientId));
        assertFalse(jsonResult.contains(expectedProductId));
        assertFalse(jsonResult.contains(expectedPrice));
        assertFalse(jsonResult.contains(expectedQtd));
    }

    @Test
    public void testCreate_Success() throws Exception {

        OrderVO orderVO = new OrderVO();
        orderVO.setKey(1L);
        orderVO.setClientId(1);
        orderVO.setProductId(1);
        orderVO.setPrice(100D);
        orderVO.setQtd(10);


        when(orderService.create(any())).thenReturn(orderVO);


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/order/")
                .content(objectMapper.writeValueAsString(orderVO))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expectedKey = "1";
        String expectedClientId = "1";
        String expectedProductId = "1";
        String expectedPrice = "100.0";
        String expectedQtd = "10";

        assertNotNull(result.getResponse());

        String jsonResult = result.getResponse().getContentAsString();
        assertTrue(jsonResult.contains(expectedKey));
        assertTrue(jsonResult.contains(expectedClientId));
        assertTrue(jsonResult.contains(expectedProductId));
        assertTrue(jsonResult.contains(expectedQtd));

    }

    @Test
    public void testCreate_Error() throws Exception {

        OrderVO orderVO = new OrderVO();
        orderVO.setKey(1L);
        orderVO.setClientId(1);
        orderVO.setProductId(1);
        orderVO.setPrice(100D);
        orderVO.setQtd(10);


        when(orderService.create(any())).thenReturn(orderVO);


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/order/")
                .content(objectMapper.writeValueAsString(orderVO))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expectedKey = "2";
        String expectedClientId = "2";
        String expectedProductId = "2";
        String expectedPrice = "200.0";
        String expectedQtd = "20";

        assertNotNull(result.getResponse());

        String jsonResult = result.getResponse().getContentAsString();
        assertFalse(jsonResult.contains(expectedKey));
        assertFalse(jsonResult.contains(expectedClientId));
        assertFalse(jsonResult.contains(expectedProductId));
        assertFalse(jsonResult.contains(expectedPrice));
        assertFalse(jsonResult.contains(expectedQtd));
    }

    @Test
    public void testUpdate_Success() throws Exception {

        OrderVO orderVO = new OrderVO();
        orderVO.setKey(1L);
        orderVO.setClientId(1);
        orderVO.setProductId(1);
        orderVO.setPrice(100D);
        orderVO.setQtd(10);

        when(orderService.update(any())).thenReturn(orderVO);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/order/")
                .content(objectMapper.writeValueAsString(orderVO))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expectedKey = "1";
        String expectedClientId = "1";
        String expectedProductId = "1";
        String expectedPrice = "100.0";
        String expectedQtd = "10";


        assertNotNull(result.getResponse());

        String jsonResult = result.getResponse().getContentAsString();
        assertTrue(jsonResult.contains(expectedKey));
        assertTrue(jsonResult.contains(expectedClientId));
        assertTrue(jsonResult.contains(expectedProductId));
        assertTrue(jsonResult.contains(expectedQtd));
    }

    @Test
    public void testUpdate_Error() throws Exception {

        OrderVO orderVO = new OrderVO();
        orderVO.setKey(1L);
        orderVO.setClientId(1);
        orderVO.setProductId(1);
        orderVO.setPrice(100D);
        orderVO.setQtd(10);


        when(orderService.update(any())).thenReturn(orderVO);


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/order/")
                .content(objectMapper.writeValueAsString(orderVO))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expectedKey = "2";
        String expectedClientId = "2";
        String expectedProductId = "2";
        String expectedPrice = "200.0";
        String expectedQtd = "20";


        assertNotNull(result.getResponse());

        String jsonResult = result.getResponse().getContentAsString();

        assertFalse(jsonResult.contains(expectedKey));
        assertFalse(jsonResult.contains(expectedClientId));
        assertFalse(jsonResult.contains(expectedProductId));
        assertFalse(jsonResult.contains(expectedPrice));
        assertFalse(jsonResult.contains(expectedQtd));
    }

}
