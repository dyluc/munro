package com.example.scottishhills.controller;

import com.example.scottishhills.model.ScottishHill;
import com.example.scottishhills.service.FilteredRequest;
import com.example.scottishhills.service.ScottishHillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ScottishHillController {

    private final ScottishHillService hillService;

    @Autowired
    public ScottishHillController(ScottishHillService hillService) {
        this.hillService = hillService;
    }

    // enabling paging by default can be accomplished with @PageableDefault annotation. E.g.
    // @PageableDefault(size = 30, sort = "field", direction = Sort.Direction.DESC)

    @GetMapping("/hills")
    public List<ScottishHill> get(
            @RequestParam Map<String, String> params,
            Sort sort) {
        FilteredRequest filteredRequest = new FilteredRequest(params, sort);
        return hillService.get(filteredRequest);
    }

}
