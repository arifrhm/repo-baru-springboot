package com.dibimbing.dibimbing.sp.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.dibimbing.dibimbing.model.KaryawanMybatis;

public interface KaryawanServiceMybatis {

    KaryawanMybatis selectBlog(Long rqid);

    List<KaryawanMybatis> selectList(String rqnama);

    Map deleteProcedure(Long resid, String reserordesc, Integer reserorcode);

    Map insertProcedure(Long resid, String resnama, String resjk, Date resdob, String resalamat, String resstatus, String reserordesc, Integer reserorcode);

    Map updateProcedure(Long resid, String resnama, String resjk, Date resdob, String resalamat, String resstatus, String reserordesc, Integer reserorcode);

}
