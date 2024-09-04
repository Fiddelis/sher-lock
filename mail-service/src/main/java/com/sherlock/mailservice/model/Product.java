package com.sherlock.mailservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {
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

    @JsonProperty("pass_code")
    private String passCode;

    @JsonProperty("estimated_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private Date estimatedDate;

    @JsonProperty("inserted_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private Date insertedDate;

    @JsonProperty("withdrawn_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private Date withdrawnDate;

    @JsonProperty("opening_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private Date openingDate;

    @JsonProperty("closing_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private Date closingDate;
}
