package com.dibimbing.assignment3.service;

import com.dibimbing.assignment3.model.KaryawanTraining;

import java.util.Map;

public interface KaryawanTrainingService {

    public Map insert(KaryawanTraining request);

    public Map delete(Long kry);
}
