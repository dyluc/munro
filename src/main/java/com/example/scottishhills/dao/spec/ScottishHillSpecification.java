package com.example.scottishhills.dao.spec;

import com.example.scottishhills.model.ScottishHill;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;


public class ScottishHillSpecification implements Specification<ScottishHill> {

    private final List<SearchCriteria> criteriaList;

    public ScottishHillSpecification(SearchCriteria... criteria) {
        criteriaList = List.of(criteria);
    }

    @Override
    public Predicate toPredicate(Root<ScottishHill> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        Predicate[] predicates = criteriaList.stream()
                .map(c -> mapToPredicate(c, root, builder))
                .toArray(Predicate[]::new);

        return builder.and(predicates);
    }

    private Predicate mapToPredicate(SearchCriteria criteria, Root<ScottishHill> root, CriteriaBuilder builder) {
        return switch(criteria.getOperator()) {
            case GREATER_THAN_EQUAL ->
                    builder.greaterThanOrEqualTo(
                            root.get(criteria.getField()),
                            criteria.getValue().toString()
                    );
            case LESS_THAN_EQUAL ->
                    builder.lessThanOrEqualTo(
                            root.get(criteria.getField()),
                            criteria.getValue().toString()
                    );
            case EQUAL ->
                    builder.equal(
                            root.get(criteria.getField()),
                            criteria.getValue().toString()
                    );
        };
    }
}
