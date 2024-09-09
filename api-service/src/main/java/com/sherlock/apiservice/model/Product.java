package com.sherlock.apiservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
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

    @JsonProperty("withdrawn_code")
    private String withdrawnCode;

    @JsonProperty("delivery_code")
    private String deliveryCode;

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
