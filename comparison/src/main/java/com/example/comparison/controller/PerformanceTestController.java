package com.example.comparison.controller;

import com.example.comparison.service.PerformanceTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/performance")
public class PerformanceTestController {
    @Autowired
    private PerformanceTestService testService;
    @PostMapping("/test")
    public Map<String, Object> runPerformanceTest() {
        Map<String, Object> results = new HashMap<>();

        // Test with JPA
        testService.deleteWithJPA();
        long jpaInsertTime = testService.insertWithJPA(100000);
        long jpaSelectTime = testService.selectWithJPA();
        long jpaDeleteTime = testService.deleteWithJPA();

        // Test without JPA
        long jdbcInsertTime = testService.insertWithoutJPA(100000);
        long jdbcSelectTime = testService.selectWithoutJPA();
        long jdbcDeleteTime = testService.deleteWithoutJPA();

        results.put("a_jpa_insert_ms", jpaInsertTime);
        results.put("b_jpa_select_ms", jpaSelectTime);
        results.put("c_jpa_delete_ms", jpaDeleteTime);
        results.put("d_jdbc_insert_ms", jdbcInsertTime);
        results.put("e_jdbc_select_ms", jdbcSelectTime);
        results.put("f_jdbc_delete_ms", jdbcDeleteTime);

        return results;
    }
}
