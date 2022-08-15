package com.dibimbing.dibimbing.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dibimbing.dibimbing.model.Karyawan;
import com.dibimbing.dibimbing.model.KaryawanTraining;
import com.dibimbing.dibimbing.model.Rekening;
import com.dibimbing.dibimbing.model.Training;
import com.dibimbing.dibimbing.repository.KaryawanRepository;
import com.dibimbing.dibimbing.repository.RekeningRepository;
import com.dibimbing.dibimbing.service.RekeningService;
import com.dibimbing.dibimbing.utils.TemplateResponse;

import java.util.Date;
import java.util.Map;

@Service
public class RekeningServiceImpl implements RekeningService {

    @Autowired
    public KaryawanRepository karyawanRepository;

    @Autowired
    public TemplateResponse templateResponse;

    @Autowired
    public RekeningRepository rekeningRepository;

    @Override
    public Map insert(Rekening rekening, Long idkaryawan) {

        try {
            if (templateResponse.chekNull(rekening.getNama())) {
                return templateResponse.templateEror("Nama is Requiered");
            }
            if(templateResponse.chekNull(rekening.getNomor())){
                return   templateResponse.templateEror("Nomor Rekening Tidak boleh null");
            }
            Karyawan checkIdKaryawan = karyawanRepository.getbyIDKaryawan(idkaryawan);
            if (templateResponse.chekNull(checkIdKaryawan)) {
                return templateResponse.templateEror("Id Karyawan Not found");
            }

            rekening.setKaryawan(checkIdKaryawan);
            Rekening rekeningSave = rekeningRepository.save(rekening);
            return templateResponse.templateSukses(rekeningSave);
        } catch (Exception e) {
            return templateResponse.templateEror(e);
        }
    }

    @Override
    public Map update(Rekening rekening, Long idkaryawan) {
        try {
            if (templateResponse.chekNull(rekening.getId())) {
                return templateResponse.templateEror("Id Rekening is required");
            }
            Rekening chekIdRekening = rekeningRepository.getbyID(rekening.getId());
            if (templateResponse.chekNull(chekIdRekening)) {
                return templateResponse.templateEror("Id Rekening Not found");
            }
            Karyawan checkIdKaryawan = karyawanRepository.getbyIDKaryawan(idkaryawan);
            if (templateResponse.chekNull(checkIdKaryawan)) {
                return templateResponse.templateEror("Id Karyawan Not found");
            }
            chekIdRekening.setNama(rekening.getNama());
            chekIdRekening.setJenis(rekening.getJenis());
            chekIdRekening.setNomor(rekening.getNomor());
            Rekening doSave = rekeningRepository.save(chekIdRekening);
            return templateResponse.templateSukses(doSave);
        } catch (Exception e) {
            return templateResponse.templateEror(e);
        }
    }

    @Override
    public Map delete(Long rekening) {
        try {
            if (templateResponse.chekNull(rekening)) {
                return templateResponse.templateEror("Id Rekening is required");
            }
            Rekening checkIdRekening = rekeningRepository.getbyID(rekening);
            if (templateResponse.chekNull(checkIdRekening)) {
                return templateResponse.templateEror("Id Rekening Not found");
            }

//            2. update , tanggal deleted saja
            checkIdRekening.setDeleted_date(new Date());//
            rekeningRepository.save(checkIdRekening);

            return templateResponse.templateSukses("sukses deleted : \n"+checkIdRekening);

        } catch (Exception e) {
            return templateResponse.templateEror(e);
        }
    }
}
