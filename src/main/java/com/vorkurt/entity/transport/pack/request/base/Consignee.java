package com.vorkurt.entity.transport.pack.request.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vorkurt.entity.address.base.BaseAddress;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;


@Table(name = "cosignee")
@Entity
@NoArgsConstructor
@Data
public class Consignee extends BaseAddress {

    @Column
    private int apartment;

    @Column
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date date;
    public Consignee(int apartment, String street, String city, String number, String postalCode ) {
        this.apartment = apartment;
        this.setCity(city);
        this.setStreet(street);
        this.setNumber(number);
        this.setPostalCode(postalCode);
        this.date = new Date();
    }
}
