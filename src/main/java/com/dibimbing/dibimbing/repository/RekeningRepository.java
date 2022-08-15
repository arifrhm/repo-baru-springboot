package com.dibimbing.dibimbing.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dibimbing.dibimbing.model.Rekening;


@Repository
public interface RekeningRepository extends PagingAndSortingRepository<Rekening, Long> {


    @Query("select r from Rekening r Where r.id = :id")
    public Rekening getbyID(@Param("id") Long id);

    public Page<Rekening> findByNama (String nama, Pageable pageable);

    @Query("select r from Rekening r")
    public Page<Rekening> getAllData (Pageable pageable);


}
