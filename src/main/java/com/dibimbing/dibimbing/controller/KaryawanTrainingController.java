package com.dibimbing.dibimbing.controller;

import com.dibimbing.dibimbing.model.Rekening;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dibimbing.dibimbing.model.Karyawan;
import com.dibimbing.dibimbing.model.KaryawanTraining;
import com.dibimbing.dibimbing.repository.KaryawanTrainingRepository;
import com.dibimbing.dibimbing.service.KaryawanTrainingService;
import com.dibimbing.dibimbing.utils.TemplateResponse;

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

    @PutMapping("/update/{idKaryawanTraining}")
    public ResponseEntity<Map>update(@PathVariable(value = "idKaryawanTraining") Long idKaryawanTraining,
                                     @RequestBody KaryawanTraining karyawanTraining ){
        Map rek = karyawanTrainingService.update(karyawanTraining,idKaryawanTraining);
        return new ResponseEntity<Map>(rek, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map>delete(@PathVariable(value = "id")Long id){
        Map kry = karyawanTrainingService.delete(id);
        return new ResponseEntity<Map>(kry, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Map>listKaryawanTraining(@RequestParam() Integer page,
                                         @RequestParam() Integer size){
        Map list = karyawanTrainingService.getAll(size, page);
        return new ResponseEntity<Map>(list, new HttpHeaders(), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Map>listKaryawanTraining(@PathVariable(value = "id")Long id){
        Map list = karyawanTrainingService.getbyIDKaryawan(id);
        return new ResponseEntity<Map>(list, new HttpHeaders(), HttpStatus.OK);

    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Map> barangById(@PathVariable(value = "id") Long id) {
//        Map map = barangService.getByID(id);
//        return new ResponseEntity<Map>(map, HttpStatus.OK);
//    }

}
