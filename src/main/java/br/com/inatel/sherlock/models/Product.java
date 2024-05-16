package br.com.inatel.sherlock.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Product {
    @Id
    private Long id;

    @JsonProperty("client_id")
    private Long clientId;

    @JsonProperty("drawer_id")
    private Long drawerId;

    private String name;

    private Double quantity;

    private Double weight;

    private String dimension;

    @JsonProperty("estimated_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime estimatedDate;

    @JsonProperty("departure_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime arrivalDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime departureDate;

    @JsonProperty("pass_code")
    private String passCode;

    private Boolean available;
}
