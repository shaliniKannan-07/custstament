package com.cognizant.customerstatement.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Records {

        @JsonProperty("Reference")
        private String transRef;

        @JsonProperty("AccountNumber")
        private String accNumber;

        @JsonProperty("Description")
        private String description;

        @JsonProperty("Start Balance")
        private String startBalance;

        @JsonProperty("Mutation")
        private String mutation;

        @JsonProperty("End Balance")
        private String endBalance;

}
