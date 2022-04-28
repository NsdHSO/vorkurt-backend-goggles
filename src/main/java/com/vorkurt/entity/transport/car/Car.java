package com.vorkurt.entity.transport.car;

import com.vorkurt.entity.transport.car.base.CarBase;
import com.vorkurt.entity.transport.driver.Driver;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@Data
@Table(name = "cars")
public class Car extends CarBase {

    @ManyToOne()
    @JoinColumn(name = "driver_id")
    private Driver driver;

    public Car (Car car){
        this.setReservoirFuel(car.getReservoirFuel());
        this.setPalateNumber(car.getPalateNumber());
        this.setDriver(car.getDriver());
        this.setKgPerWeight(car.getKgPerWeight());
    }

}
