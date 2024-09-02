package com.sherlock.apiservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Drawer {
    @Id
    private Integer id;
    private Integer lockerId;
    private String dimension;
    private Boolean available;
}
