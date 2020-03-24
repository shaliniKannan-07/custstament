package com.cognizant.customerstatement.service;

import com.cognizant.customerstatement.exception.JsonParsingException;
import com.cognizant.customerstatement.model.StatementResponse;
import com.cognizant.customerstatement.model.Records;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.nio.charset.StandardCharsets;
import java.util.*;

@Slf4j
@Service
public class CustomerService {

    @Autowired
    private Validator validator;

    public StatementResponse jsonPasser(MultipartFile file) {
        log.info("inside service");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String fileString = new String(file.getBytes(), StandardCharsets.UTF_8);
            List<Records> customer = objectMapper.readValue(fileString, new TypeReference<List<Records>>() {
            });
            return validator.validateRecords(customer);

        } catch (Exception exception) {
            throw new JsonParsingException("Error during parsing JSON");
        }

    }
}


