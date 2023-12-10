package com.idstar.lat5.controller;

import com.idstar.lat5.model.Rekening;
import com.idstar.lat5.repository.RekeningRepository;
import com.idstar.lat5.service.RekeningService;
import com.idstar.lat5.utils.TemplateResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/rekening")
public class RekeningController {

    @Autowired
    RekeningService rekeningService;

    @Autowired
    RekeningRepository rekeningRepository;

    @Autowired
    public TemplateResponse templateResponse;


    @PostMapping("/add/{idkaryawan}")
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map> save(@PathVariable(value = "idkaryawan") Long idkaryawan,
                                    @Validated @RequestBody Rekening objModel) {
        Map obj = rekeningService.insert(objModel, idkaryawan);
        return new ResponseEntity<Map>(obj, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Map> update(@RequestBody Rekening objModel) {
        Map obj = rekeningService.update(objModel);
        return new ResponseEntity<Map>(obj, HttpStatus.OK);
    }

    @PutMapping("/update/{idkaryawan}")
    public ResponseEntity<Map> update(@PathVariable(value = "idkaryawan") Long idkaryawan,
                                      @RequestBody Rekening objModel) {
        Map map = rekeningService.update(objModel, idkaryawan);
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map> delete(@PathVariable(value = "id") Long id) {
        Map map = rekeningService.delete(id);
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

}
