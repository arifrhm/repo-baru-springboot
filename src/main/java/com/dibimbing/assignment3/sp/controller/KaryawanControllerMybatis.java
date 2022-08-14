package com.dibimbing.assignment3.sp.controller;


import com.dibimbing.assignment3.model.Karyawan;
import com.dibimbing.assignment3.model.KaryawanMybatis;
import com.dibimbing.assignment3.sp.service.KaryawanServiceMybatis;
import com.dibimbing.assignment3.utils.TemplateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/sp/karyawan")
public class KaryawanControllerMybatis {

    @Autowired
    public KaryawanServiceMybatis karyawanServiceMybatis;

    @Autowired
    private TemplateResponse templateResponse;

    @PostMapping("/save")
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map> save(@Valid @RequestBody Karyawan kryModel){
        Map kry = karyawanServiceMybatis.insertProcedure(kryModel.getId(), kryModel.getNama(), kryModel.getJk(), kryModel.getDob(), kryModel.getAlamat(), kryModel.getStatus(), kryModel.getNik(), kryModel.getNpwp(), null,null);
        return new ResponseEntity<>(kry, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Map> update(@Valid @RequestBody Karyawan kryModel){
        Map kry = karyawanServiceMybatis.updateProcedure(kryModel.getId(), kryModel.getNama(), kryModel.getJk(), kryModel.getDob(), kryModel.getAlamat(), kryModel.getStatus(), kryModel.getNik(), kryModel.getNpwp(), null,null);
        return new ResponseEntity<>(kry, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map> delete(@PathVariable(value = "id") Long resid){
        Map kry = karyawanServiceMybatis.deleteProcedure(resid, "Ok",200);
        return new ResponseEntity<>(kry, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Map> listByNama(@RequestParam(required = false) String nama){
        List<KaryawanMybatis> list = karyawanServiceMybatis.selectList("%"+nama+"%");
        return new ResponseEntity<>(templateResponse.templateSukses(templateResponse.conversiToKaryawan(list)), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map> listById(@PathVariable(value = "id") Long id) {
        KaryawanMybatis obj = karyawanServiceMybatis.selectBlog(id);
        return new ResponseEntity<>(templateResponse.templateSukses(templateResponse.conversiToKaryawan(obj)), HttpStatus.OK);
    }



}
