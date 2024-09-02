package com.sherlock.apiservice.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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

    @JsonProperty("client_id")
    private Integer clientId;

    @JsonProperty("drawer_id")
    private Integer drawerId;

    @JsonProperty("locker_id")
    private Integer lockerId;

    private Float quantity;

    private String name;

    private String dimension;

    private String address;

    @JsonProperty("pass_code")
    private String passCode;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date estimatedDate;
}
