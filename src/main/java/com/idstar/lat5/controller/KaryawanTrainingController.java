package com.idstar.lat5.controller;

import com.idstar.lat5.dao.KaryawanTrainingRequest;
import com.idstar.lat5.model.KaryawanTraining;
import com.idstar.lat5.repository.KaryawanTrainingRepository;
import com.idstar.lat5.service.KaryawanTrainingService;
import com.idstar.lat5.utils.TemplateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/training-karyawan")
public class KaryawanTrainingController {

    @Autowired
    public KaryawanTrainingService karyawanTrainingService;

    @Autowired
    public KaryawanTrainingRepository karyawanTrainingRepository;

    @Autowired
    public TemplateResponse templateResponse;

    @PostMapping("")
    public ResponseEntity<Map> save(@RequestBody KaryawanTrainingRequest objModel) {
        Map obj = karyawanTrainingService.insert(objModel);
        return new ResponseEntity<Map>(obj, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<Map> update(@RequestBody KaryawanTrainingRequest objModel) {
        Map map = karyawanTrainingService.update(objModel);
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map> delete(@PathVariable(value = "id") Long id) {
        Map map = karyawanTrainingService.delete(id);
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Map> listByNama(
            @RequestParam() Integer page,
            @RequestParam() Integer size,
            @RequestParam(required = false) String namaKaryawan,// ga mandatory : default mandatory
            @RequestParam(required = false) String temaTraining) {
        Map map = new HashMap();
        Page<KaryawanTraining> list = null;
        Pageable show_data = PageRequest.of(page, size, Sort.by("id").descending());//batasin roq

        if ( namaKaryawan != null && !namaKaryawan.isEmpty() ) {
            list = karyawanTrainingRepository.findByKaryawanNamaLike("%" + namaKaryawan + "%", show_data);
        }else
        if ( temaTraining != null && !temaTraining.isEmpty() ) {
            list = karyawanTrainingRepository.findByTrainingTemaLike("%" + temaTraining + "%", show_data);
        } else {
            list = karyawanTrainingRepository.getAllData(show_data);
        }
        return new ResponseEntity<Map>(templateResponse.templateSukses(list), new HttpHeaders(), HttpStatus.OK);
    }
}

