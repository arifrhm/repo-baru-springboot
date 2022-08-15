package com.dibimbing.dibimbing.model;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KaryawanMyBatisMapper implements RowMapper<KaryawanMybatis> {

    @Override
    public KaryawanMybatis mapRow(ResultSet rs, int rowNum) throws SQLException {

        KaryawanMybatis karyawan = new KaryawanMybatis();

        karyawan.setResid(rs.getLong("resid"));
        karyawan.setResnama(rs.getString("resnama"));
        karyawan.setResalamat(rs.getString("resalamat"));
        karyawan.setResdob(rs.getDate("resdob"));
        karyawan.setResjk(rs.getString("resjk"));
        karyawan.setResnik(rs.getString("resnik"));
        karyawan.setResnpwp(rs.getString("resnpwp"));
        karyawan.setResstatus(rs.getString("resstatus"));
        karyawan.setReserorcode(rs.getInt("reserorcode"));
        karyawan.setReserordesc(rs.getString("reserordesc"));

        return karyawan;
    }
}
