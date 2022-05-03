package com.vorkurt.entity.transport.pack.base;

import lombok.Data;

import javax.persistence.*;

@Data
@MappedSuperclass
public abstract class DescriptionBase {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}
