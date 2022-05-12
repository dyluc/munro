package com.example.scottishhills.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class ScottishHillIT {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @Autowired
    public ScottishHillIT(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    @DisplayName("Should get all hills")
    public void shouldGetAllHills() throws Exception {

        MvcResult mvcResult =
                mockMvc.perform(
                                get("/api/v1/hills")
                                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                        .andExpect(status().isOk())
                        .andReturn();

        String json = mvcResult.getResponse().getContentAsString();
        JsonNode expectedJson = objectMapper.readTree(new ClassPathResource("all_hills.json").getFile());
        JsonNode actualJson = objectMapper.readTree(json);
        assertEquals(expectedJson, actualJson);

    }

    @Test
    @DisplayName("Should get all Tops")
    public void shouldGetAllTops() throws Exception {

        MvcResult mvcResult = mockMvc.perform(
                        get("/api/v1/hills?category=TOP")
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();
        JsonNode expectedJson = objectMapper.readTree(new ClassPathResource("all_tops.json").getFile());
        JsonNode actualJson = objectMapper.readTree(json);
        assertEquals(expectedJson, actualJson);
    }

    @Test
    @DisplayName("Should get all Munros within height range, sorted by height,name limit 20")
    public void shouldGetAllMunsWithinRangeSortedAndLimited() throws Exception {

        MvcResult mvcResult = mockMvc.perform(
                        get("/api/v1/hills?category=MUN&min_height=952.4&max_height=970&sort=height,name&limit=20")
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();
        JsonNode expectedJson = objectMapper.readTree(new ClassPathResource("all_muns_within_range_sorted_limited.json").getFile());
        JsonNode actualJson = objectMapper.readTree(json);
        assertEquals(expectedJson, actualJson);

    }

    // TODO: add more tests
}
