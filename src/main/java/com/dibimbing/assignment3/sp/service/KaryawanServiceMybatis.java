package com.dibimbing.assignment3.sp.service;

import com.dibimbing.assignment3.model.KaryawanMybatis;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface KaryawanServiceMybatis {

    KaryawanMybatis selectBlog(Long rqid);

    List<KaryawanMybatis> selectList(String rqnama);

    Map deleteProcedure(Long resid, String reserordesc, Integer reserorcode);

    Map insertProcedure(Long resid, String resnama, String resjk, Date resdob, String resalamat, String resstatus, String resnik, String resnpwp, String reserordesc, Integer reserorcode);
    Map updateProcedure(Long resid, String resnama, String resjk, Date resdob, String resalamat, String resstatus, String resnik, String resnpwp, String reserordesc, Integer reserorcode);

}
