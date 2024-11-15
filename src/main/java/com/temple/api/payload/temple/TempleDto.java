package com.temple.api.payload.temple;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TempleDto {

    private Long templeID;

    private String templeName;

    private String templeCode;

    private String templeAddress;

    private Date addedDate;

    private String AddUID;

    private String location;

    private Date dateEstablished;

    private String contactPerson;

    private String contactNumber;

    private String email;

    private String templeType;

    private double annualRevenue;

    private String website;

}
