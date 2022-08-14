package com.dibimbing.assignment3.repository;

import com.dibimbing.assignment3.model.Karyawan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface KaryawanRepository extends PagingAndSortingRepository<Karyawan, Long> {


    @Query("select k from Karyawan k")
    public Page<Karyawan> getAllData(Pageable pageable);

    @Query("select k from Karyawan k where k.id = :idkaryawan")
    public Karyawan getbyIDKaryawan(@Param("idkaryawan") Long idbebas);

    @Query("select k from Karyawan k where k.nama = :nama")
    public Page<Karyawan> listNamaKaryawan(String nama, Pageable pageable);

}
