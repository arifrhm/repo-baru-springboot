package com.dibimbing.assignment3.controller;

import com.dibimbing.assignment3.model.Karyawan;
import com.dibimbing.assignment3.model.KaryawanTraining;
import com.dibimbing.assignment3.repository.KaryawanTrainingRepository;
import com.dibimbing.assignment3.service.KaryawanTrainingService;
import com.dibimbing.assignment3.utils.TemplateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/v1/karyawan-training")
public class KaryawanTrainingController {

    @Autowired
    public KaryawanTrainingRepository karyawanTrainingRepository;

    @Autowired
    public KaryawanTrainingService karyawanTrainingService;

    @Autowired
    public TemplateResponse templateResponse;

    @PostMapping("/save")
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map> save(@Valid @RequestBody KaryawanTraining kryTraining){
        Map kry = karyawanTrainingService.insert(kryTraining);
        return new ResponseEntity<Map>(kry, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map>delete(@PathVariable(value = "id")Long id){
        Map kry = karyawanTrainingService.delete(id);
        return new ResponseEntity<Map>(kry, HttpStatus.OK);
    }

//    @GetMapping("/list")
//    public ResponseEntity<Map>listByNama(@RequestParam() Integer page,
//                                         @RequestParam() Integer size){
//        Map list = karyawanTrainingService.(size, page);
//        return new ResponseEntity<Map>(list, new HttpHeaders(), HttpStatus.OK);
//
//    }


}
