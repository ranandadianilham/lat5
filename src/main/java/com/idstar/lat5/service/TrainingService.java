package com.idstar.lat5.service;

import com.idstar.lat5.model.Training;

import java.util.Map;

public interface TrainingService {

    public Map insert(Training obj);

    public Map update(Training obj);

    public Map delete(Long idTraining);
}
