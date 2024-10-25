package com.sherlock.mailservice.model;

import lombok.*;

@Getter
@Setter
@ToString

@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
    Client client;
    Product product;
}
