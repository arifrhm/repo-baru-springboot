package com.dibimbing.assignment3.controller;

import com.dibimbing.assignment3.model.Karyawan;
import com.dibimbing.assignment3.model.Rekening;
import com.dibimbing.assignment3.repository.RekeningRepository;
import com.dibimbing.assignment3.service.RekeningService;
import com.dibimbing.assignment3.utils.TemplateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/v1/rekening")
public class RekeningController {
    @Autowired
    public TemplateResponse  templateResponse;

    @Autowired
    public RekeningRepository rekeningRepository;

    @Autowired
    public RekeningService rekeningService;

    @PostMapping("/save/{idkaryawan}")
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map> save(@PathVariable(value = "idkaryawan") Long idkaryawan, @Valid @RequestBody Rekening rekModel){
        Map rek = rekeningService.insert(rekModel, idkaryawan);
        return new ResponseEntity<Map>(rek, HttpStatus.OK);
    }

    @PutMapping("/update/{idkaryawan}")
    public ResponseEntity<Map>update(@PathVariable(value = "idkaryawan") Long idkaryawan,
    @RequestBody Rekening rekModel ){
        Map rek = rekeningService.update(rekModel,idkaryawan);
        return new ResponseEntity<Map>(rek, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map>delete(@PathVariable(value = "id")Long id){
        Map rek = rekeningService.delete(id);
        return new ResponseEntity<Map>(rek, HttpStatus.OK);
    }
}
