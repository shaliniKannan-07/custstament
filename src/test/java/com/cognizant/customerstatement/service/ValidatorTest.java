package com.cognizant.customerstatement.service;

import com.cognizant.customerstatement.model.StatementResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ValidatorTest {

    CustomerService service = new CustomerService();

    @Test
    void validateSuccess() {
        try {
            MultipartFile file = new MockMultipartFile("src/test/resources/testStatus1.json",
                    new FileInputStream(new File("src/test/resources/testStatus1.json")));
            StatementResponse statementResponse = service.jsonPasser(file);
            assertEquals("SUCCESSFUL", statementResponse.getResult());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void validateDuplicateReference() {
        try {
            MultipartFile file = new MockMultipartFile("src/test/resources/testStatus2.json",
                    new FileInputStream(new File("src/test/resources/testStatus2.json")));
            StatementResponse statementResponse = service.jsonPasser(file);
            assertEquals("DUPLICATE_REFERENCE", statementResponse.getResult());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void validateIncorrectBalance() {
        try {
            MultipartFile file = new MockMultipartFile("src/test/resources/testStatus3.json",
                    new FileInputStream(new File("src/test/resources/testStatus3.json")));
            StatementResponse statementResponse = service.jsonPasser(file);
            assertEquals("INCORRECT_END_BALANCE", statementResponse.getResult());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void validateDupulicateAndBalance() {
        try {
            MultipartFile file = new MockMultipartFile("src/test/resources/testStatus4.json",
                    new FileInputStream(new File("src/test/resources/testStatus4.json")));
            StatementResponse statementResponse = service.jsonPasser(file);
            assertEquals("DUPLICATE_REFERENCE_INCORRECT_END_BALANCE", statementResponse.getResult());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}