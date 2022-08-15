package com.dibimbing.dibimbing.service;

import java.util.Map;

import com.dibimbing.dibimbing.model.Training;

public interface TrainingService {

    public Map insert(Training training);
    public Map update(Training training);
    public Map findByTema(String tema, Integer page, Integer size);
    public Map findByPengajar(String pengajar, Integer page, Integer size);

    public Map getByID(Long idTraining);
    public Map getAll(int size, int page);

}
