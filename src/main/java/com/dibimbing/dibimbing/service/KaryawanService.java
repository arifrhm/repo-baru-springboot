package com.dibimbing.dibimbing.service;

import java.util.Map;

import com.dibimbing.dibimbing.model.Karyawan;

public interface KaryawanService {
    public Map insert(Karyawan karyawan);
    public Map update(Karyawan karyawan);
    public Map delete(Long karyawan);
    public Map getAll(int size, int page);
    public Map getbyIDKaryawan(Long karyawanID);
    public Map listNamaKaryawan(String nama, Integer page, Integer size);

}
