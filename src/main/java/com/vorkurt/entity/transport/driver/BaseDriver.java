package com.vorkurt.entity.transport.driver;

import com.vorkurt.entity.address.Address;
import lombok.Data;

import javax.persistence.*;

@MappedSuperclass
@Data
public abstract class BaseDriver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "driver_license")
    private String driverLicense;

    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "address_id")
    private Address address;

}
