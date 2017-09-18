package com.edisonren.nmm.dao;

import com.edisonren.nmm.v1.NmmModel;

import java.util.Map;

/**
 * Created by edison on 9/13/17.
 */
public interface NmmModelRepository {
    public void saveNmmModel(NmmModel model);
    public void updateNmmModel(NmmModel model);

    public Map<String, NmmModel> findNmmModelsByServiceName(String serviceName);
    public NmmModel getNmmModel(String serviceName, String scenarioId);

    public Long deleteNmmModel(String serviceName, String scenarioId);
}
