package com.example.scottishhills.service;

import com.example.scottishhills.dao.spec.ScottishHillSpecification;
import com.example.scottishhills.dao.spec.SearchCriteria;
import com.example.scottishhills.dao.spec.SearchOperator;

import java.util.Map;

import static com.example.scottishhills.service.FilteredRequest.SearchParam;

public class SpecConstructor {

    /**
     * Constructs a {@link ScottishHillSpecification} from the given search parameters.
     */
    public static ScottishHillSpecification createSpec(Map<SearchParam, Object> searchParams) {

        SearchCriteria[] searchCriteria = searchParams.entrySet().stream()
                .map(param -> mapParamToCriteria(param.getKey(), param.getValue()))
                .toArray(SearchCriteria[]::new);

        // create spec
        return new ScottishHillSpecification(searchCriteria);
    }

    private static SearchCriteria mapParamToCriteria(SearchParam param, Object value) {
        return switch(param) { // ?category=MUN - TOP
            case CATEGORY -> new SearchCriteria("category", SearchOperator.EQUAL, value);
            case MIN_HEIGHT -> new SearchCriteria("height", SearchOperator.GREATER_THAN_EQUAL, value);
            case MAX_HEIGHT -> new SearchCriteria("height", SearchOperator.LESS_THAN_EQUAL, value);
        };
    }

}
