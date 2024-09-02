package com.sherlock.apiservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    private Integer id;
    private Integer clientId;
    private Integer drawerId;
    private Integer lockerId;
    private Float quantity;
    private String name;
    private String dimension;
    private String address;
    private String passCode;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date estimatedDate;
}
