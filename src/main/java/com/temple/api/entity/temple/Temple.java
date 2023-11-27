package com.temple.api.entity.temple;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(
        name = "Temple"
)
public class Temple {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long templeID;

    @Column(nullable = false)
    private String templeName;

    private String location;

    private String templeCode;

    private String templeAddress;

    private Date addedDate;

    private String AddUID;

    private Date dateEstablished;

    private String contactPerson;

    private String contactNumber;

    private String email;

    private String templeType;

    private double annualRevenue;

    private String website;

}
