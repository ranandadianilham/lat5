package com.idstar.lat5.controller;


import com.idstar.lat5.model.Karyawan;
import com.idstar.lat5.repository.KaryawanRepository;
import com.idstar.lat5.service.KaryawanService;
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
@RequestMapping("/v1/karyawan")
public class KaryawanController {

    @Autowired
    KaryawanService karyawanService;

    @Autowired
    KaryawanRepository karyawanRepository;

    @Autowired
    TemplateResponse templateResponse;

    @GetMapping("/hello")
    public HashMap<String, String>  greeting() {
        HashMap<String, String> map = new HashMap<>();
        map.put("key", "value");
        map.put("foo", "bar");
        map.put("aa", "bb");
        return map;
    }

    @PostMapping("/add")
    public ResponseEntity<Map> save(@RequestBody Karyawan objModel) {
        Map map = new HashMap();
        Map obj = karyawanService.insertKryAndDetail(objModel);
        return new ResponseEntity<Map>(obj, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Map> update(@RequestBody Karyawan objModel) { // request degan type PUT method
        Map obj = karyawanService.updateKryAndDetail(objModel); // sini logig
        return new ResponseEntity<Map>(obj, HttpStatus.OK);// response
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map> delete(@PathVariable(value = "id") Long id) {
        Map map = karyawanService.delete(id);
        return new ResponseEntity<Map>(map, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Map> listByBama(
            @RequestParam() Integer page,
            @RequestParam() Integer size,
            @RequestParam(required = false) String nama) {
        Map map = new HashMap();
        Page<Karyawan> list = null;
        Pageable show_data = PageRequest.of(page, size, Sort.by("id").descending());//batasin roq

        if ( nama != null && !nama.isEmpty() ) {
            list = karyawanRepository.findByNamaLike("%" + nama + "%", show_data);
        } else {
            list = karyawanRepository.getAllData(show_data);
        }
        return new ResponseEntity<Map>(templateResponse.templateSukses(list), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map> getId(@PathVariable(value = "id") Long id) {
       Karyawan obj1 = karyawanRepository.getByID(id);
        return new ResponseEntity<Map>(templateResponse.templateSukses(obj1), HttpStatus.OK);
    }


}
