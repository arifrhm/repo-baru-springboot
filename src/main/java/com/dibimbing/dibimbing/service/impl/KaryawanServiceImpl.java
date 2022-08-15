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
import com.dibimbing.dibimbing.repository.KaryawanRepository;
import com.dibimbing.dibimbing.service.KaryawanService;
import com.dibimbing.dibimbing.utils.TemplateResponse;

import java.util.Date;
import java.util.Map;

@Service
public class KaryawanServiceImpl implements KaryawanService {

    @Autowired
    public KaryawanRepository karyawanRepository;

    @Autowired
    public TemplateResponse templateResponse;

    public static final Logger log = LoggerFactory.getLogger(KaryawanServiceImpl.class);

    @Override
    public Map insert(Karyawan karyawan) {
        try {
            if (templateResponse.chekNull(karyawan.getNama())) {
                return templateResponse.templateEror("Nama is Requiered");
            }

            Karyawan karyawanSave = karyawanRepository.save(karyawan);
            return templateResponse.templateSukses(karyawanSave);
        } catch (Exception e) {
            return templateResponse.templateEror(e);
        }
    }

    @Override
    public Map update(Karyawan karyawan) {
        try {
            if (templateResponse.chekNull(karyawan.getId())) {
                return templateResponse.templateEror("Id Karyawan is required");
            }
            Karyawan chekIdKaryawan = karyawanRepository.getbyIDKaryawan(karyawan.getId());
            if (templateResponse.chekNull(chekIdKaryawan)) {
                return templateResponse.templateEror("Id Karyawan Not found");
            }

            chekIdKaryawan.setNama(karyawan.getNama());
            chekIdKaryawan.setJk(karyawan.getJk());
            chekIdKaryawan.setDob(karyawan.getDob());
            chekIdKaryawan.setAlamat(karyawan.getAlamat());
            chekIdKaryawan.setStatus(karyawan.getStatus());


            Karyawan doSave = karyawanRepository.save(chekIdKaryawan);
            return templateResponse.templateSukses(doSave);
        } catch (Exception e) {
            return templateResponse.templateEror(e);
        }
    }

    @Override
    public Map delete(Long karyawan) {
        try {
            if (templateResponse.chekNull(karyawan)) {
                return templateResponse.templateEror("Id Karyawan is required");
            }

            Karyawan chekIdKaryawan = karyawanRepository.getbyIDKaryawan(karyawan);
            if (templateResponse.chekNull(chekIdKaryawan)) {
                return templateResponse.templateEror("Id Karyawan Not found");
            }

            chekIdKaryawan.setDeleted_date(new Date());//
            karyawanRepository.save(chekIdKaryawan);

            return templateResponse.templateSukses("sukses deleted : \n"+karyawanRepository);

        } catch (Exception e) {
            return templateResponse.templateEror(e);
        }
    }

    @Override
    public Map getAll(int size, int page) {
        try {
            Pageable show_data = PageRequest.of(page, size);
            Page<Karyawan> list = karyawanRepository.getAllData(show_data);
            return templateResponse.templateSukses(list);
        } catch (Exception e) {
            log.error("ada eror di method getAll:" + e);
            return templateResponse.templateEror(e);
        }

    }

    @Override
    public Map getbyIDKaryawan(Long karyawanID) {
        try {
            Karyawan karyawanById = karyawanRepository.getbyIDKaryawan(karyawanID);
            return templateResponse.templateSukses(karyawanById);
        } catch (Exception e) {
            log.error("ada error di method getAll:" + e);
            return templateResponse.templateEror(e);
        }
    }

    @Override
    public Map listNamaKaryawan(String nama, Integer page, Integer size) {
        try {
            Pageable show_data = PageRequest.of(page, size);
            Page<Karyawan> list = karyawanRepository.listNamaKaryawan(nama, show_data);
            return templateResponse.templateSukses(list);
        } catch (Exception e) {

            log.error("eror disini findByNama : " + e);
            //menampilkan responose
            return templateResponse.templateEror(e);
        }

    }
}
