package com.wif3006.igsservice.customer.models;

import lombok.Data;

@Data
public class CustomerModel {
    private Long id;
    private String companyName;
    private String pic;
    private String addrLine1;
    private String addrLine2;
    private String postcode;
    private String city;
    private String state;
    private String country;
    private String tel;
    private String fax;
    private String email;
}
