package com.sherlock.mailservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Client {
    private Integer id;

    private String address;

    private String name;

    @JsonProperty("phone_number")
    private String phoneNumber;

    private String mail;
}
