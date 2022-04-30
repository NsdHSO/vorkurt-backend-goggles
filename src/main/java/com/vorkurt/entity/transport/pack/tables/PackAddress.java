package com.vorkurt.entity.transport.pack.tables;

import com.vorkurt.entity.address.base.BaseAddress;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "package_address")
public class PackAddress extends BaseAddress {

    @Column
    private int scale;

    @Column
    private int apartment;

    @Column
    private String postalCode;
}
