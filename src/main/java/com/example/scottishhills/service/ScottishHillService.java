package com.example.scottishhills.service;

import com.example.scottishhills.dao.ScottishHillRepository;
import com.example.scottishhills.dao.spec.ScottishHillSpecification;
import com.example.scottishhills.model.ScottishHill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScottishHillService {

    private final ScottishHillRepository hillRepository;

    @Autowired
    public ScottishHillService(ScottishHillRepository hillRepository) {
        this.hillRepository = hillRepository;
    }

    public List<ScottishHill> get(FilteredRequest filteredRequest) {

        var searchParams = filteredRequest.getSearchParams();
        var limit = filteredRequest.getLimit();
        var sort = filteredRequest.getSort();

        // create spec
        ScottishHillSpecification spec = SpecConstructor.createSpec(searchParams);

        return hillRepository.findAll(spec, PageRequest.of(0, limit, sort)).toList();
    }



}
