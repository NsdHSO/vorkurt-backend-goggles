package com.vorkurt.entity.address.base;

import lombok.Data;

import javax.persistence.*;

@MappedSuperclass
@Data
public abstract class BaseAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String street;

    @Column
    private String city;
}
