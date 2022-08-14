package com.dibimbing.assignment3.dao;

import lombok.Data;

@Data
public class KaryawanTrainingRequest {

    public Long id;
    public Long idKaryawan;
    public Long idTraining;
    public String tema;
    public String pengajar;

}
