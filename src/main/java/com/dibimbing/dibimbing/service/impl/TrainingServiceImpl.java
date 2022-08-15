package com.dibimbing.dibimbing.service.impl;

import com.dibimbing.dibimbing.model.KaryawanTraining;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dibimbing.dibimbing.model.Karyawan;
import com.dibimbing.dibimbing.model.Training;
import com.dibimbing.dibimbing.repository.TrainingRepository;
import com.dibimbing.dibimbing.service.TrainingService;
import com.dibimbing.dibimbing.utils.TemplateResponse;

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

    @Override
    public Map getByID(Long id) {
        try {
            Training trainingById = trainingRepository.getbyID(id);
            return templateResponse.templateSukses(trainingById);
        } catch (Exception e) {
            log.error("ada error di method getAll:" + e);
            return templateResponse.templateEror(e);
        }
    }
}
