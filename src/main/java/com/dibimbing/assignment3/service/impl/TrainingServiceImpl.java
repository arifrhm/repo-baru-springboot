package com.dibimbing.assignment3.service.impl;

import com.dibimbing.assignment3.model.Karyawan;
import com.dibimbing.assignment3.model.Training;
import com.dibimbing.assignment3.repository.TrainingRepository;
import com.dibimbing.assignment3.service.TrainingService;
import com.dibimbing.assignment3.utils.TemplateResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TrainingServiceImpl implements TrainingService {

    @Autowired
    public TemplateResponse templateResponse;

    @Autowired
    public TrainingRepository trainingRepository;

    public static final Logger log = LoggerFactory.getLogger(KaryawanServiceImpl.class);

    @Override
    public Map insert(Training training) {
        try {
            if (templateResponse.chekNull(training.getTema())) {
                return templateResponse.templateEror("Tema is Requiered");
            }
            if (templateResponse.chekNull(training.getPengajar())) {
                return templateResponse.templateEror("Pengajar is Requiered");
            }

            Training trainingSave = trainingRepository.save(training);
            return templateResponse.templateSukses(trainingSave);
        } catch (Exception e) {
            return templateResponse.templateEror(e);
        }
    }

    @Override
    public Map update(Training training) {

        try {
            if (templateResponse.chekNull(training.getId())) {
                return templateResponse.templateEror("Id Training is required");
            }
            Training chekIdTraining = trainingRepository.getbyID(training.getId());
            if (templateResponse.chekNull(chekIdTraining)) {
                return templateResponse.templateEror("Id Training Not found");
            }

            chekIdTraining.setTema(training.getTema());
            chekIdTraining.setPengajar(training.getPengajar());

            Training doSave = trainingRepository.save(chekIdTraining);
            return templateResponse.templateSukses(doSave);

        } catch (Exception e) {
            return templateResponse.templateEror(e);
        }
    }

    @Override
    public Map findByTema(String tema, Integer page, Integer size) {
        try {
            Pageable show_data = PageRequest.of(page, size);
            Page<Karyawan> list = trainingRepository.findByTema(tema, show_data);
            return templateResponse.templateSukses(list);
        } catch (Exception e) {

            log.error("eror disini findByTema : " + e);
            return templateResponse.templateEror(e);
        }
    }

    @Override
    public Map findByPengajar(String pengajar, Integer page, Integer size) {
        try {
            Pageable show_data = PageRequest.of(page, size);
            Page<Training> list = trainingRepository.findByPengajar(pengajar, show_data);
            return templateResponse.templateSukses(list);
        } catch (Exception e) {

            log.error("eror disini findByPengajar : " + e);
            return templateResponse.templateEror(e);
        }
    }

    @Override
    public Map getAll(int size, int page) {
        try {
            Pageable show_data = PageRequest.of(page, size);
            Page<Training> list = trainingRepository.getAllData(show_data);
            return templateResponse.templateSukses(list);
        } catch (Exception e) {
            log.error("ada eror di method getAll:" + e);
            return templateResponse.templateEror(e);
        }
    }
}
