package com.vorkurt.entity.transport.driver;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vorkurt.entity.transport.car.Car;
import com.vorkurt.entity.transport.driver.request.DriverRequest;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@Table(name = "drivers")
@EnableAutoConfiguration
public class Driver extends BaseDriver {


    @Column
    @OneToMany(mappedBy = "driver")
    @ToString.Exclude
    private List<Car> cars;

    public Driver(DriverRequest driverReq) {
        this.setDriverLicense(driverReq.getDriverLicense());
        this.setFirstName(driverReq.getFirstName());
        this.setLastName(driverReq.getLastName());
        this.setAddress(driverReq.getAddress());
    }

    public void addCars(List<Car> car){
        if(this.cars == null ){
            cars = new ArrayList<>();
        }
        cars = car;
    }
}
