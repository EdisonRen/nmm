package com.edisonren.nmm.dao;

import com.edisonren.nmm.v1.NmmModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * Created by edison on 9/13/17.
 */
@Repository
public class NmmModelRepositoryImpl implements NmmModelRepository {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations ops;

    public NmmModelRepositoryImpl() { }

    @PostConstruct
    public void init() {
        ops = redisTemplate.opsForHash();
    }

    @Override
    public void saveNmmModel(NmmModel model) {
        ops.putIfAbsent(model.getScenario().getServiceName(), model.getScenarioInfo().getScenarioId(), model);
        logger.info("{}:{} Persisted.", model.getScenario().getServiceName(), model.getScenarioInfo().getScenarioId());
    }

    @Override
    public void updateNmmModel(NmmModel model) {
        ops.put(model.getScenario().getServiceName(), model.getScenarioInfo().getScenarioId(), model);
        logger.info("{}:{} Persisted.", model.getScenario().getServiceName(), model.getScenarioInfo().getScenarioId());
    }

    @Override
    public Map<String, NmmModel> findNmmModelsByServiceName(String serviceName) {
        return ops.entries(serviceName);
    }

    @Override
    public NmmModel getNmmModel(String serviceName, String scenarioId) {
        return (NmmModel)ops.get(serviceName, scenarioId);
    }

    @Override
    public Long deleteNmmModel(String serviceName, String scenarioId) {
        return ops.delete(serviceName, scenarioId);
    }
}
