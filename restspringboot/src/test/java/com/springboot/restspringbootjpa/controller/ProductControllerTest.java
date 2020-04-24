package com.springboot.restspringbootjpa.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.restspringbootjpa.data.vo.ProductVO;
import com.springboot.restspringbootjpa.service.ProductService;
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
        ProductController.class
})
public class ProductControllerTest {

    @Autowired
    private ProductController controller;

    @MockBean
    private ProductService productService;

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

        List<ProductVO> listResponse = new ArrayList<>();
        ProductVO response = new ProductVO();
        response.setKey(1L);
        response.setName("teste");
        response.setPrice(10.0D);
        response.setQtd(3);

        listResponse.add(response);

        when(productService.findByAll()).thenReturn(listResponse);


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/product/")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expectedKey = "1";
        String expectedTiTle = "teste";
        String expectedPrice = "10.0";
        String expectedQtd = "3";


        assertNotNull(result.getResponse());

        String jsonResult = result.getResponse().getContentAsString();
        assertTrue(jsonResult.contains(expectedKey));
        assertTrue(jsonResult.contains(expectedTiTle));
        assertTrue(jsonResult.contains(expectedPrice));
        assertTrue(jsonResult.contains(expectedQtd));
    }

    @Test
    public void testFindByAll_Error() throws Exception {

        List<ProductVO> listResponse = new ArrayList<>();
        ProductVO response = new ProductVO();
        response.setKey(1L);
        response.setName("teste");
        response.setPrice(10.0D);
        response.setQtd(3);

        listResponse.add(response);

        when(productService.findByAll()).thenReturn(listResponse);


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/product/")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expectedKey = "2";
        String expectedTiTle = "teste 2";
        String expectedPrice = "20.0";
        String expectedQtd = "4";


        assertNotNull(result.getResponse());

        String jsonResult = result.getResponse().getContentAsString();
        assertFalse(jsonResult.contains(expectedKey));
        assertFalse(jsonResult.contains(expectedTiTle));
        assertFalse(jsonResult.contains(expectedPrice));
        assertFalse(jsonResult.contains(expectedQtd));
    }

    @Test
    public void testFindById_Success() throws Exception {

        ProductVO response = new ProductVO();
        response.setKey(1L);
        response.setName("teste");
        response.setPrice(10.0D);
        response.setQtd(3);


        when(productService.findById(any())).thenReturn(response);


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/product/1")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expectedKey = "1";
        String expectedTiTle = "teste";
        String expectedPrice = "10.0";
        String expectedQtd = "3";


        assertNotNull(result.getResponse());

        String jsonResult = result.getResponse().getContentAsString();
        assertTrue(jsonResult.contains(expectedKey));
        assertTrue(jsonResult.contains(expectedTiTle));
        assertTrue(jsonResult.contains(expectedPrice));
        assertTrue(jsonResult.contains(expectedQtd));
    }

    @Test
    public void testFindByid_Error() throws Exception {

        ProductVO response = new ProductVO();
        response.setKey(1L);
        response.setName("teste");
        response.setPrice(10.0D);
        response.setQtd(3);


        when(productService.findById(any())).thenReturn(response);


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/product/1")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expectedKey = "2";
        String expectedTiTle = "teste teste";
        String expectedPrice = "30.0";
        String expectedQtd = "4";


        assertNotNull(result.getResponse());

        String jsonResult = result.getResponse().getContentAsString();
        assertFalse(jsonResult.contains(expectedKey));
        assertFalse(jsonResult.contains(expectedTiTle));
        assertFalse(jsonResult.contains(expectedPrice));
        assertFalse(jsonResult.contains(expectedQtd));
    }

    @Test
    public void testCreate_Success() throws Exception {

        ProductVO productVO = new ProductVO();
        productVO.setKey(1L);
        productVO.setName("teste");
        productVO.setPrice(10.0D);
        productVO.setQtd(3);


        when(productService.create(any())).thenReturn(productVO);


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/product/")
                .content(objectMapper.writeValueAsString(productVO))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expectedKey = "1";
        String expectedTiTle = "teste";
        String expectedPrice = "10.0";
        String expectedQtd = "3";


        assertNotNull(result.getResponse());

        String jsonResult = result.getResponse().getContentAsString();
        assertTrue(jsonResult.contains(expectedKey));
        assertTrue(jsonResult.contains(expectedTiTle));
        assertTrue(jsonResult.contains(expectedPrice));
        assertTrue(jsonResult.contains(expectedQtd));
    }

    @Test
    public void testCreate_Error() throws Exception {

        ProductVO productVO = new ProductVO();
        productVO.setKey(1L);
        productVO.setName("teste");
        productVO.setPrice(10.0D);
        productVO.setQtd(3);


        when(productService.create(any())).thenReturn(productVO);


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/product/")
                .content(objectMapper.writeValueAsString(productVO))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expectedKey = "2";
        String expectedTiTle = "teste Teste";
        String expectedPrice = "20.0";
        String expectedQtd = "4";


        assertNotNull(result.getResponse());

        String jsonResult = result.getResponse().getContentAsString();
        assertFalse(jsonResult.contains(expectedKey));
        assertFalse(jsonResult.contains(expectedTiTle));
        assertFalse(jsonResult.contains(expectedPrice));
        assertFalse(jsonResult.contains(expectedQtd));
    }

    @Test
    public void testUpdate_Success() throws Exception {

        ProductVO productVO = new ProductVO();
        productVO.setKey(1L);
        productVO.setName("teste");
        productVO.setPrice(10.0D);
        productVO.setQtd(3);


        when(productService.update(any())).thenReturn(productVO);


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/product/")
                .content(objectMapper.writeValueAsString(productVO))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expectedKey = "1";
        String expectedTiTle = "teste";
        String expectedPrice = "10.0";
        String expectedQtd = "3";


        assertNotNull(result.getResponse());

        String jsonResult = result.getResponse().getContentAsString();
        assertTrue(jsonResult.contains(expectedKey));
        assertTrue(jsonResult.contains(expectedTiTle));
        assertTrue(jsonResult.contains(expectedPrice));
        assertTrue(jsonResult.contains(expectedQtd));
    }

    @Test
    public void testUpdate_Error() throws Exception {

        ProductVO productVO = new ProductVO();
        productVO.setKey(1L);
        productVO.setName("teste");
        productVO.setPrice(10.0D);
        productVO.setQtd(3);


        when(productService.update(any())).thenReturn(productVO);


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/product/")
                .content(objectMapper.writeValueAsString(productVO))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expectedKey = "2";
        String expectedTiTle = "teste Teste";
        String expectedPrice = "20.0";
        String expectedQtd = "4";


        assertNotNull(result.getResponse());

        String jsonResult = result.getResponse().getContentAsString();
        assertFalse(jsonResult.contains(expectedKey));
        assertFalse(jsonResult.contains(expectedTiTle));
        assertFalse(jsonResult.contains(expectedPrice));
        assertFalse(jsonResult.contains(expectedQtd));
    }

}
