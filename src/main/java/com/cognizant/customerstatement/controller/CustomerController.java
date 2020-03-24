package com.cognizant.customerstatement.controller;

import com.cognizant.customerstatement.model.StatementResponse;
import com.cognizant.customerstatement.service.CustomerService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/records")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/run")
    public StatementResponse readJsonWithObjectMapper(@RequestParam("file")MultipartFile file) {
        log.info("Inside Controller");
        return customerService.jsonPasser(file);
    }
}
