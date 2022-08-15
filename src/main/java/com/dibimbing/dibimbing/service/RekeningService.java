package com.dibimbing.dibimbing.service;

import java.util.Map;

import com.dibimbing.dibimbing.model.Karyawan;
import com.dibimbing.dibimbing.model.Rekening;

public interface RekeningService {

    public Map insert(Rekening rekening, Long idkaryawan);
    public Map update(Rekening rekening, Long idkaryawan);
    public Map delete(Long rekening);
}
