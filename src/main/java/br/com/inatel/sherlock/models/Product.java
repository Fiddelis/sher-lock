package br.com.inatel.sherlock.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Product {
    @Id
    private Integer id;

    private Integer clientId;

    @JsonProperty("drawer_id")
    private Integer drawerId;

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

    private String passCode;

    private Boolean available;
}
