package com.dibimbing.dibimbing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dibimbing.dibimbing.model.Karyawan;
import com.dibimbing.dibimbing.repository.KaryawanRepository;
import com.dibimbing.dibimbing.service.KaryawanService;
import com.dibimbing.dibimbing.utils.TemplateResponse;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/v1/karyawan")
public class KaryawanController {

    @Autowired
    public KaryawanService karyawanService;

    @Autowired
    public KaryawanRepository karyawanRepository;

    @Autowired
    public TemplateResponse templateResponse;

    @PostMapping("/save")
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map>save(@Valid @RequestBody Karyawan kryModel){
        Map kry = karyawanService.insert(kryModel);
        return new ResponseEntity<Map>(kry, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Map>update(@RequestBody Karyawan kryModel){
        Map kry = karyawanService.update(kryModel);
        return new ResponseEntity<Map>(kry, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map>delete(@PathVariable(value = "id")Long id){
        Map kry = karyawanService.delete(id);
        return new ResponseEntity<Map>(kry, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Map>listByNama(@RequestParam() Integer page,
                                         @RequestParam() Integer size){
        Map list = karyawanService.getAll(size, page);
        return new ResponseEntity<Map>(list, new HttpHeaders(), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Map>karyawanByID(@PathVariable(value = "id")Long id){
        Map dataKaryawan = karyawanService.getbyIDKaryawan(id);
        return new ResponseEntity<Map>(dataKaryawan, new HttpHeaders(), HttpStatus.OK);

    }

}
