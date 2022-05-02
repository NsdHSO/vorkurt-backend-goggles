package com.vorkurt.entity.transport.car;

import com.vorkurt.entity.transport.car.base.CarBase;
import com.vorkurt.entity.transport.driver.Driver;
import com.vorkurt.entity.transport.pack.Pack;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Table(name = "cars")
public class Car extends CarBase {

    @ManyToOne()
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @Column
    @OneToMany(mappedBy= "carId")
    private List<Pack> packs;


    public Car (Car car){
        this.setReservoirFuel(car.getReservoirFuel());
        this.setPlateNumber(car.getPlateNumber());
        this.setDriver(car.getDriver());
        this.setKgPerWeight(car.getKgPerWeight());
    }

}
