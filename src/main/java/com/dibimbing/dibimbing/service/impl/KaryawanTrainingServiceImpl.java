package com.dibimbing.dibimbing.service.impl;

import com.dibimbing.dibimbing.model.Rekening;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dibimbing.dibimbing.model.Karyawan;
import com.dibimbing.dibimbing.model.KaryawanTraining;
import com.dibimbing.dibimbing.model.Training;
import com.dibimbing.dibimbing.repository.KaryawanRepository;
import com.dibimbing.dibimbing.repository.KaryawanTrainingRepository;
import com.dibimbing.dibimbing.repository.TrainingRepository;
import com.dibimbing.dibimbing.service.KaryawanTrainingService;
import com.dibimbing.dibimbing.utils.TemplateResponse;

import java.util.Date;
import java.util.Map;


@Service
public class KaryawanTrainingServiceImpl implements KaryawanTrainingService {

    @Autowired
    public KaryawanTrainingRepository karyawanTrainingRepository;

    @Autowired
    public KaryawanRepository karyawanRepository;

    @Autowired
    public TrainingRepository trainingRepository;

    @Autowired
    public TemplateResponse templateResponse;

    public static final Logger log = LoggerFactory.getLogger(KaryawanTrainingServiceImpl.class);

    @Override
    public Map insert(KaryawanTraining request) {

        try {

            if(templateResponse.chekNull(request.getTraining_date())){
                return   templateResponse.templateEror("training_date Tidak boleh kosong!!");
            }
            // Training chekIdtTraining = trainingRepository.getbyID(request.getTraining().getId());
            // if(templateResponse.chekNull(chekIdtTraining)){
            //     return   templateResponse.templateEror("Id Training Tidak boleh null");
            // }
            Karyawan chekIdKaryawan =  karyawanRepository.getbyIDKaryawan(request.getKaryawan().getId());
            if(templateResponse.chekNull(chekIdKaryawan)){
                return   templateResponse.templateEror("Id Karyawan Tidak ada di database");
            }

            Training chekIdTraining =  trainingRepository.getbyID(request.getTraining().getId());
            if(templateResponse.chekNull(chekIdTraining)){
                return   templateResponse.templateEror("Id Training Tidak ada di database");
            }
            
            //disimpan ke db: objek karyawan training
            KaryawanTraining objSave = new KaryawanTraining();
            objSave.setKaryawan(chekIdKaryawan);
            objSave.setTraining(chekIdTraining);
            objSave.setTraining_date(objSave.getTraining_date());
            KaryawanTraining doSave = karyawanTrainingRepository.save(request);
            return templateResponse.templateSukses(doSave);
        }catch (Exception e){
            return templateResponse.templateEror(e);
        }

    }

    @Override
    public Map delete(Long kry) {
        try {
            if (templateResponse.chekNull(kry)) {
                return templateResponse.templateEror("Id Karyawan Training is required");
            }

            KaryawanTraining chekIdKaryawanTraining = karyawanTrainingRepository.getbyID(kry);
            if (templateResponse.chekNull(chekIdKaryawanTraining)) {
                return templateResponse.templateEror("Id Karyawan Not found");
            }

            chekIdKaryawanTraining.setDeleted_date(new Date());//
            karyawanTrainingRepository.save(chekIdKaryawanTraining);

            return templateResponse.templateSukses("sukses deleted : \n"+karyawanTrainingRepository);

        } catch (Exception e) {
            return templateResponse.templateEror(e);
        }
    }

    @Override
    public Map update(KaryawanTraining karyawanTrainingReq, Long idKaryawanTraining) {
        try {

            KaryawanTraining chekIdBkaryawanTraining = karyawanTrainingRepository.getbyID(idKaryawanTraining);
            if (templateResponse.chekNull(chekIdBkaryawanTraining)) {
                return templateResponse.templateEror("Id Karyawan Training Not found");
            }
            Karyawan chekIdKaryawan = karyawanRepository.getbyIDKaryawan(karyawanTrainingReq.getKaryawan().getId());
            if (templateResponse.chekNull(chekIdKaryawan)) {
                return templateResponse.templateEror("Id Karyawan Not found");
            }
            Training chekIdTraining = trainingRepository.getbyID(karyawanTrainingReq.getTraining().getId());
            if (templateResponse.chekNull(chekIdTraining)) {
                return templateResponse.templateEror("Id Training Not found");
            }

            chekIdBkaryawanTraining.setTraining_date(karyawanTrainingReq.getTraining_date());
            chekIdBkaryawanTraining.setTraining(chekIdTraining);
            chekIdBkaryawanTraining.setKaryawan(chekIdKaryawan);

            KaryawanTraining dosave = karyawanTrainingRepository.save(chekIdBkaryawanTraining);

            return templateResponse.templateSukses(dosave);
        } catch (Exception e) {
            return templateResponse.templateEror(e);
        }
    }

    @Override
    public Map getAll(int size, int page) {
        try {
            Pageable show_data = PageRequest.of(page, size);
            Page<KaryawanTraining> list = karyawanTrainingRepository.getAllData(show_data);
            return templateResponse.templateSukses(list);
        } catch (Exception e) {
            log.error("ada error di method getAll:" + e);
            return templateResponse.templateEror(e);
        }
    }

    @Override
    public Map getbyIDKaryawan(Long id) {
        try {
            KaryawanTraining karyawanTrainingById = karyawanTrainingRepository.getbyID(id);
            return templateResponse.templateSukses(karyawanTrainingById);
        } catch (Exception e) {
            log.error("ada error di method getAll:" + e);
            return templateResponse.templateEror(e);
        }
    }

}
