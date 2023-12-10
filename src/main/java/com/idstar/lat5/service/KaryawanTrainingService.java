package com.idstar.lat5.service;



import com.idstar.lat5.dao.KaryawanTrainingRequest;

import java.util.Map;

public interface KaryawanTrainingService {

    public Map insert(KaryawanTrainingRequest obj);

    public Map update(KaryawanTrainingRequest obj);

    public Map delete(Long obj);
}
