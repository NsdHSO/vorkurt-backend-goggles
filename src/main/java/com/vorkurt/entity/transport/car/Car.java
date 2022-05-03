package com.vorkurt.entity.transport.car;

import com.fasterxml.jackson.annotation.*;
import com.vorkurt.entity.transport.car.base.CarBase;
import com.vorkurt.entity.transport.driver.Driver;
import com.vorkurt.entity.transport.pack.Pack;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@NoArgsConstructor
@Table(name = "cars")
@Data
public class Car extends CarBase {

    @ManyToOne()
    @JoinColumn(name = "driver_id")
    @JsonBackReference
    private Driver driver;

    @Column
    @OneToMany(mappedBy= "carId", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Pack> packs;


    public Car (Car car){
        this.setReservoirFuel(car.getReservoirFuel());
        this.setPlateNumber(car.getPlateNumber());
        this.setDriver(car.getDriver());
        this.setKgPerWeight(car.getKgPerWeight());

    }

}
