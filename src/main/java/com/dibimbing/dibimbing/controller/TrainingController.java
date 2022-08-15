package com.dibimbing.dibimbing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dibimbing.dibimbing.model.Training;
import com.dibimbing.dibimbing.repository.TrainingRepository;
import com.dibimbing.dibimbing.service.TrainingService;
import com.dibimbing.dibimbing.utils.TemplateResponse;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/training")
public class TrainingController {

    @Autowired
    public TrainingRepository trainingRepository;

    @Autowired
    public TrainingService trainingService;

    @Autowired
    public TemplateResponse templateResponse;

    @PostMapping("/save")
    public ResponseEntity<Map> save(@RequestBody Training objTraining){
        Map map = new HashMap();
        Map obj = trainingService.insert(objTraining);
        return new ResponseEntity<Map>(obj, HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<Map> update(@RequestBody Training objTraining) {
        Map map = trainingService.update(objTraining);
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

    @GetMapping("list")
    public ResponseEntity<Map> listByNama(@RequestParam() Integer page, @RequestParam() Integer size){
        Map list = trainingService.getAll(size, page);
        return new ResponseEntity<Map>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map>listKaryawanTraining(@PathVariable(value = "id")Long id){
        Map training = trainingService.getByID(id);
        return new ResponseEntity<Map>(training, new HttpHeaders(), HttpStatus.OK);

    }
}
