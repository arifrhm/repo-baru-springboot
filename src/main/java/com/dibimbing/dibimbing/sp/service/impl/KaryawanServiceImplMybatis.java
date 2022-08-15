package com.dibimbing.dibimbing.sp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.dibimbing.dibimbing.model.Karyawan;
import com.dibimbing.dibimbing.model.KaryawanMybatis;
import com.dibimbing.dibimbing.sp.repository.KaryawanRepoMybatis;
import com.dibimbing.dibimbing.sp.service.KaryawanServiceMybatis;
import com.dibimbing.dibimbing.utils.QuerySP;
import com.dibimbing.dibimbing.utils.TemplateResponse;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KaryawanServiceImplMybatis implements KaryawanServiceMybatis {

    @Autowired
    KaryawanRepoMybatis karyawanRepoMybatis;

    @Autowired
    public TemplateResponse templateResponse;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    QuerySP querySP;


    @Override
    public KaryawanMybatis selectBlog(Long rqid) {
        return karyawanRepoMybatis.selectBlog(rqid);
    }

    @Override
    public List<KaryawanMybatis> selectList(String rqnama) {
        return karyawanRepoMybatis.selectList(rqnama);
    }

    @Override
    public Map deleteProcedure(Long resid, String reserordesc, Integer reserorcode) {
        jdbcTemplate.execute(querySP.deletekaryawan);

        Map<String , Object> map = new HashMap<>();
        map.put("resid",(resid == null ? 0: resid));
        map.put("reserordesc",reserordesc);
        map.put("reserorcode",reserorcode);

        karyawanRepoMybatis.deleteProcedure(map);
        System.out.println("resid="+ map.get("resid"));
        System.out.println("reserordesc="+ map.get("reserordesc"));
        System.out.println("reserorcode="+ map.get("reserorcode"));

        Karyawan objKaryawan = new Karyawan();
        objKaryawan.setId(Long.parseLong(String.valueOf(map.get("resid"))));


        return templateResponse.templateSukses(objKaryawan, (String) map.get("reserordesc"), String.valueOf(map.get("reserorcode")));
    }

    @Override
    public Map insertProcedure(Long resid, String resnama, String resjk, Date resdob, String resalamat, String resstatus, String reserordesc, Integer reserorcode) {
        jdbcTemplate.execute(querySP.save);

        Map<String , Object> map = new HashMap<>();
//        map.put("resid",resid);
        map.put("resnama",resnama);
        map.put("resjk",resjk);
        map.put("resdob",resdob);
        map.put("resalamat",resalamat);
        map.put("resstatus",resstatus);
        map.put("reserordesc",reserordesc);
        map.put("reserorcode",reserorcode);

        karyawanRepoMybatis.insertProcedure(map);
        System.out.println("resid="+ map.get("resid"));
        System.out.println("reserordesc="+ map.get("reserordesc"));
        System.out.println("reserorcode="+ map.get("reserorcode"));



        Karyawan objKaryawan = new Karyawan();
        objKaryawan.setId(Long.parseLong(String.valueOf(map.get("resid"))));
        objKaryawan.setNama((String) map.get("resnama"));
        objKaryawan.setJk((String) map.get("resjk"));
        objKaryawan.setDob((Date) map.get("resdob"));
        objKaryawan.setAlamat((String) map.get("resalamat"));
        objKaryawan.setStatus((String) map.get("resstatus"));
        objKaryawan.setDob((Date) map.get("resdob"));

        return templateResponse.templateSukses(objKaryawan, (String) map.get("reserordesc"), String.valueOf(map.get("reserorcode")));
    }

    @Override
    public Map updateProcedure(Long resid, String resnama, String resjk, Date resdob, String resalamat, String resstatus, String reserordesc, Integer reserorcode) {

        Map<String , Object> map = new HashMap<>();
        map.put("resnama",resnama);
        map.put("resjk",resjk);
        map.put("resdob",resdob);
        map.put("resalamat",resalamat);
        map.put("resstatus",resstatus);
        map.put("resid",(resid == null ? 0: resid));
        map.put("reserordesc",reserordesc);
        map.put("reserorcode",reserorcode);

        karyawanRepoMybatis.updateProcedure(map);
        System.out.println("resid="+ map.get("resid"));
        System.out.println("reserordesc="+ map.get("reserordesc"));
        System.out.println("reserorcode="+ map.get("reserorcode"));

        Karyawan objKaryawan = new Karyawan();
        objKaryawan.setId(Long.parseLong(String.valueOf(map.get("resid"))));
        objKaryawan.setNama((String) map.get("resnama"));
        objKaryawan.setJk((String) map.get("resjk"));
        objKaryawan.setDob((Date) map.get("resdob"));
        objKaryawan.setAlamat((String) map.get("resalamat"));
        objKaryawan.setStatus((String) map.get("resstatus"));
        objKaryawan.setDob((Date) map.get("resdob"));


        return templateResponse.templateSukses(objKaryawan, (String) map.get("reserordesc"), String.valueOf(map.get("reserorcode")));
    }

}
