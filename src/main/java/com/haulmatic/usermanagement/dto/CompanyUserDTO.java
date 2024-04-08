package com.haulmatic.usermanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyUserDTO {

        private String id;
        private String firstName;
        private String lastName;
        private String email;
        private String age;
}
