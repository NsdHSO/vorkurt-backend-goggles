package com.vorkurt.entity.transport.pack.request.base;

import com.vorkurt.entity.address.base.BaseAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Table(name = "cosignee")
@Entity
@NoArgsConstructor
@Data
public class Cosignee extends BaseAddress {

    @Column
    private int apartment;

    public Cosignee(int apartment, String street, String city, String number, String postalCode ) {
        this.apartment = apartment;
        this.setCity(city);
        this.setStreet(street);
        this.setNumber(number);
        this.setPostalCode(postalCode);
    }
}
