package com.dibimbing.dibimbing.sp.repository;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import com.dibimbing.dibimbing.model.KaryawanMybatis;

import java.util.List;
import java.util.Map;

@Mapper
public interface KaryawanRepoMybatis {
    @Insert("call savekaryawan(" +
            "#{resid, mode=INOUT, jdbcType=BIGINT}," +
            "#{resnama, mode=INOUT, jdbcType=VARCHAR}," +
            "#{resjk, mode=INOUT, jdbcType=VARCHAR}, " +
            "#{resdob, mode=INOUT, jdbcType=DATE}," +
            "#{resalamat, mode=INOUT, jdbcType=VARCHAR}," +
            "#{resstatus, mode=INOUT, jdbcType=VARCHAR}," +
            "#{reserordesc, mode=INOUT, jdbcType=VARCHAR}," +
            "#{reserorcode, mode=INOUT, jdbcType=INTEGER})")
    @Options(statementType = StatementType.CALLABLE)
    void insertProcedure(Map<String, Object> map);


    @Update("call updatekaryawan(" +
            "#{resid, mode=INOUT, jdbcType=BIGINT}," +
            "#{resnama, mode=INOUT, jdbcType=VARCHAR}," +
            "#{resjk, mode=INOUT, jdbcType=VARCHAR}, " +
            "#{resdob, mode=INOUT, jdbcType=DATE}," +
            "#{resalamat, mode=INOUT, jdbcType=VARCHAR}," +
            "#{resstatus, mode=INOUT, jdbcType=VARCHAR}," +
            "#{reserordesc, mode=INOUT, jdbcType=VARCHAR}," +
            "#{reserorcode, mode=INOUT, jdbcType=INTEGER})")
    @Options(statementType = StatementType.CALLABLE)
    void updateProcedure(Map<String, Object> map);

    @Select("select resid, resnama, resjk, resdob, resalamat, resstatus FROM getkaryawan(#{rqid});")
    KaryawanMybatis selectBlog(Long rqid);

    @Select("select resid, resnama, resjk, resdob, resalamat, resstatus FROM getlistkaryawanbynama(#{rqnama});")
        List<KaryawanMybatis> selectList(String rqnama);

    @Delete("call deletekaryawan (" +
            "#{resid, mode=INOUT, jdbcType=BIGINT}," +
            "#{reserordesc, mode=INOUT, jdbcType=VARCHAR},"+
            "#{reserorcode, mode=INOUT, jdbcType=INTEGER})")
    @Options(statementType = StatementType.CALLABLE)
    void deleteProcedure(Map<String, Object> map);
}
