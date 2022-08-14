package com.dibimbing.assignment3.service;

import com.dibimbing.assignment3.model.Karyawan;
import com.dibimbing.assignment3.model.Rekening;

import java.util.Map;

public interface RekeningService {

    public Map insert(Rekening rekening, Long idkaryawan);
    public Map update(Rekening rekening, Long idkaryawan);
    public Map delete(Long rekening);
}
