package com.cognizant.customerstatement.service;

import com.cognizant.customerstatement.exception.JsonParsingException;
import com.cognizant.customerstatement.model.StatementResponse;
import com.cognizant.customerstatement.model.FailedStatements;
import com.cognizant.customerstatement.model.Records;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.cognizant.customerstatement.utility.Constant.*;

@Slf4j
@Component
public class Validator {

    private static DecimalFormat formatter = new DecimalFormat("0.00");


    public StatementResponse validateRecords(List<Records> customer) {
        log.info("inside validator");
        List<FailedStatements> duplicateCheck = new ArrayList<>();
        List<FailedStatements> balanceCheck = new ArrayList<>();
        FailedStatements failedStatements = new FailedStatements();
        Map<String, String> unique = new HashMap<>();
        try {
            if (customer != null) {
                for (Records records : customer) {
                    if (unique.containsKey(records.getTransRef())) {
                        failedStatements.setTransRef(records.getTransRef());
                        failedStatements.setAccNumber(records.getAccNumber());
                        duplicateCheck.add(failedStatements);
                    } else {
                        BigDecimal total = new BigDecimal(records.getStartBalance())
                                .add(new BigDecimal(records.getMutation()));
                        if (0 != total.compareTo(new BigDecimal(records.getEndBalance()))) {
                            failedStatements.setTransRef(records.getTransRef());
                            failedStatements.setAccNumber(records.getAccNumber());
                            balanceCheck.add(failedStatements);
                        }
                        unique.put(records.getTransRef(), records.getAccNumber());
                    }
                }
                log.info("duplicate" + duplicateCheck);
                log.info("end" + balanceCheck);


            }


        } catch (Exception ex) {
            throw new JsonParsingException("Error Occured");
        }
        if (duplicateCheck.isEmpty() && balanceCheck.isEmpty())
            return new StatementResponse(SUCCESSFUL, null);
        else if (!duplicateCheck.isEmpty() && balanceCheck.isEmpty())
            return new StatementResponse(DUPLICATE_REFERENCE, duplicateCheck);
        else if (duplicateCheck.isEmpty() && !balanceCheck.isEmpty())
            return new StatementResponse(INCORRECT_END_BALANCE, balanceCheck);
        else {
            for (FailedStatements i : duplicateCheck)
                balanceCheck.add(i);
            return new StatementResponse(DUPLICATE_REFERENCE_INCORRECT_END_BALANCE, balanceCheck);
        }
    }
}
