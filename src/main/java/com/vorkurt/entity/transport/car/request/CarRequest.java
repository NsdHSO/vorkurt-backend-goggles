package com.vorkurt.entity.transport.car.request;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.vorkurt.entity.transport.car.Car;
import com.vorkurt.entity.transport.car.base.CarBase;
import com.vorkurt.entity.transport.driver.Driver;
import com.vorkurt.entity.transport.pack.Pack;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@NoArgsConstructor
public class CarRequest extends CarBase {

    private Driver driver;

    @Column
    @OneToMany(mappedBy= "car", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Pack> packs;

    public CarRequest (Car car){
        this.setReservoirFuel(car.getReservoirFuel());
        this.setPlateNumber(car.getPlateNumber());
        this.setDriver(car.getDriver());
        this.setKgPerWeight(car.getKgPerWeight());

    }
}
