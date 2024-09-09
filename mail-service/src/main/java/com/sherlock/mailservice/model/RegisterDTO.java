package com.sherlock.mailservice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RegisterDTO {
    Client client;
    Product product;
}
