package com.cognizant.customerstatement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@ToString
public class StatementResponse {

    private String  result;

    private List<FailedStatements> errorRecords;
}
