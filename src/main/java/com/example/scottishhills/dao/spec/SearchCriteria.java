package com.example.scottishhills.dao.spec;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SearchCriteria {
    private String field;
    private SearchOperator operator;
    private Object value;
}
