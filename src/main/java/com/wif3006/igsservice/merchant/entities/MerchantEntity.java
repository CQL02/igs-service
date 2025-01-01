package com.wif3006.igsservice.merchant.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "MERCHANT")
public class MerchantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "COMPANY_NAME", nullable = false)
    private String companyName;

    @Column(name = "REGISTRATION_NO")
    private String registrationNo;

    @Column(name = "ADDR_LINE_1")
    private String addrLine1;

    @Column(name = "ADDR_LINE_2")
    private String addrLine2;

    @Column(name = "POSTCODE")
    private String postcode;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STATE")
    private String state;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "TEL")
    private String tel;

    @Column(name = "FAX")
    private String fax;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "BANK_NAME")
    private String bankName;

    @Column(name = "BANK_ACC_NO")
    private String bankAccNo;
}
