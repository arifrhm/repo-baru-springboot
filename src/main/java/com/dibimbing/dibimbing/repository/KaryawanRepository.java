package com.dibimbing.dibimbing.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dibimbing.dibimbing.model.Karyawan;

@Repository
public interface KaryawanRepository extends PagingAndSortingRepository<Karyawan, Long> {


    @Query("select k from Karyawan k")
    public Page<Karyawan> getAllData(Pageable pageable);

    @Query("select k from Karyawan k where k.id = :idkaryawan")
    public Karyawan getbyIDKaryawan(@Param("idkaryawan") Long idbebas);

    @Query("select k from Karyawan k where k.nama = :nama")
    public Page<Karyawan> listNamaKaryawan(String nama, Pageable pageable);

}
