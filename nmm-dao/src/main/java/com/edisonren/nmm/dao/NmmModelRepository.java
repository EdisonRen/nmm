package com.edisonren.nmm.dao;

import com.edisonren.nmm.model.NmmModel;

import java.util.Map;

/**
 * Created by edison on 9/13/17.
 */
public interface NmmModelRepository {
    public void saveNmmModel(NmmModel model);
    public void updateNmmModel(NmmModel model);
    public Map<String, NmmModel> findNmmModelsByServiceName(String serviceName);
    public void deleteNmmModel(String serviceName, String scenarioId);
}
