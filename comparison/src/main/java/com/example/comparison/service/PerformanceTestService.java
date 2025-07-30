package com.example.comparison.service;

import com.example.comparison.entity.TestEntity;
import com.example.comparison.repository.TestEntityRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PerformanceTestService {
    @Autowired
    private TestEntityRepository repository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public long insertWithJPA(int count) {
        long startTime = System.currentTimeMillis();
        List<TestEntity> entities = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            entities.add(new TestEntity("Name" + i, i));
        }
        repository.saveAll(entities);
        return System.currentTimeMillis() - startTime;
    }

    public long selectWithJPA() {
        long startTime = System.currentTimeMillis();
        List<TestEntity> results = repository.findAll();
        return System.currentTimeMillis() - startTime;
    }

    public long deleteWithJPA() {
        long startTime = System.currentTimeMillis();
        repository.deleteAll();
        return System.currentTimeMillis() - startTime;
    }

    // Raw JDBC Operations
    public long insertWithoutJPA(int count) {
        long startTime = System.currentTimeMillis();

        String sql = "INSERT INTO performance_test (name, test_value) VALUES (?, ?)";

        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, "Name" + i);
                ps.setInt(2, i);
            }

            @Override
            public int getBatchSize() {
                return count;
            }
        });

        return System.currentTimeMillis() - startTime;
    }

    public long selectWithoutJPA() {
        long startTime = System.currentTimeMillis();

        String sql = "SELECT id, name, test_value FROM performance_test";
        List<Map<String, Object>> results = jdbcTemplate.queryForList(sql);

        return System.currentTimeMillis() - startTime;
    }

    public long deleteWithoutJPA() {
        long startTime = System.currentTimeMillis();
        jdbcTemplate.update("DELETE FROM performance_test");
        return System.currentTimeMillis() - startTime;
    }
}
