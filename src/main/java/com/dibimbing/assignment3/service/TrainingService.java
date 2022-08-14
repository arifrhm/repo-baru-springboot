package com.dibimbing.assignment3.service;

import com.dibimbing.assignment3.model.Training;

import java.util.Map;

public interface TrainingService {

    public Map insert(Training training);
    public Map update(Training training);
    public Map findByTema(String tema, Integer page, Integer size);
    public Map findByPengajar(String pengajar, Integer page, Integer size);
    public Map getAll(int size, int page);

}
