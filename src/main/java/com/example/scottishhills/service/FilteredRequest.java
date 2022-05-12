package com.example.scottishhills.service;

import lombok.Getter;
import org.springframework.data.domain.Sort;

import java.util.HashMap;
import java.util.Map;

@Getter
public class FilteredRequest {

    enum SearchParam {
        CATEGORY, MIN_HEIGHT, MAX_HEIGHT
    }

    private final Map<SearchParam, Object> searchParams;
    private final Sort sort;
    private int limit = 500; // default 500 entries

    public FilteredRequest(Map<String, String> params, Sort sort) {

        searchParams = new HashMap<>();
        populateSearchParams(params);

        // aggregate functions
        this.sort = sort;
        params.remove("sort");

        int limitParam;
        if((limitParam = getInt(params.remove("limit"))) != -1)
            limit = limitParam;

        if(!params.isEmpty())
            throw new IllegalArgumentException("Invalid parameters: " + params.keySet());

    }

    private void populateSearchParams(Map<String, String> params) {
        String category;
        if((category = params.remove("category")) != null)
            searchParams.put(SearchParam.CATEGORY, category);

        float minHeight;
        if((minHeight = getFloat(params.remove("min_height"))) != -1F)
            searchParams.put(SearchParam.MIN_HEIGHT, minHeight);

        float maxHeight;
        if((maxHeight = getFloat(params.remove("max_height"))) != -1F)
            searchParams.put(SearchParam.MAX_HEIGHT, maxHeight);
    }

    private float getFloat(String value) {
        if(value == null)
            return -1F; // uninitialised

        try {
            float f = Float.parseFloat(value);
            return Math.round(f * 10.00) / 10.00F; // 1dp (1/10th of metre)
        } catch(NumberFormatException e) {
            throw new IllegalArgumentException("Invalid float parameter value: " + value);
        }
    }

    private int getInt(String value) {
        if(value == null)
            return -1; // uninitialised

        try {
            return Integer.parseInt(value);
        } catch(NumberFormatException e) {
            throw new IllegalArgumentException("Invalid integer parameter value: " + value);
        }
    }
}
