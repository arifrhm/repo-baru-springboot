package com.dibimbing.assignment3.service;

import com.dibimbing.assignment3.model.Karyawan;

import java.util.Map;

public interface KaryawanService {
    public Map insert(Karyawan karyawan);
    public Map update(Karyawan karyawan);
    public Map delete(Long karyawan);
    public Map getAll(int size, int page);
    public Map getbyIDKaryawan(Long karyawan,Integer page, Integer size);
    public Map listNamaKaryawan(String nama, Integer page, Integer size);

}
