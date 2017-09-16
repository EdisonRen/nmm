package com.edisonren.nmm.dao;

import com.edisonren.nmm.model.NmmModel;
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
        put(model);
    }

    @Override
    public void updateNmmModel(NmmModel model) {
        put(model);
    }

    @Override
    public Map<String, NmmModel> findNmmModelsByServiceName(String serviceName) {
        return ops.entries(serviceName);
    }

    @Override
    public void deleteNmmModel(String serviceName, String scenarioId) {
        ops.delete(serviceName, scenarioId);
    }

    private void put(NmmModel model) {
        ops.put(model.getScenario().getServiceName(), model.getScenarioInfo().getScenarioId(), model);
    }
}
