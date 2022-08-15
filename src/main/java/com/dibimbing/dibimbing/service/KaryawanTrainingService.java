package com.dibimbing.dibimbing.service;

import java.util.Map;

import com.dibimbing.dibimbing.model.Karyawan;
import com.dibimbing.dibimbing.model.KaryawanTraining;

public interface KaryawanTrainingService {

    public Map insert(KaryawanTraining request);

    public Map delete(Long kry);

    public Map update(KaryawanTraining karyawan, Long idKaryawanTraining);
    public Map getAll(int size, int page);
    public Map getbyIDKaryawan(Long id);
}
