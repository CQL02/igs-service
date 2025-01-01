package com.wif3006.igsservice.customer.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "CUSTOMER")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "COMPANY_NAME", nullable = false)
    private String companyName;

    @Column(name = "PIC")
    private String pic;

    @Column(name = "ADDR_LINE_1", nullable = false)
    private String addrLine1;

    @Column(name = "ADDR_LINE_2")
    private String addrLine2;

    @Column(name = "POSTCODE", nullable = false)
    private String postcode;

    @Column(name = "CITY", nullable = false)
    private String city;

    @Column(name = "STATE", nullable = false)
    private String state;

    @Column(name = "COUNTRY", nullable = false)
    private String country;

    @Column(name = "TEL")
    private String tel;

    @Column(name = "FAX")
    private String fax;

    @Column(name = "EMAIL")
    private String email;
}
