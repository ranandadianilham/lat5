package com.idstar.lat5.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/demo")
public class DemoController {

    @GetMapping
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello");
    }
}
