package com.vorkurt.entity.transport.driver.request;

import com.vorkurt.entity.transport.car.Car;
import com.vorkurt.entity.transport.car.request.CarResponse;
import com.vorkurt.entity.transport.driver.BaseDriver;
import com.vorkurt.entity.transport.driver.Driver;
import lombok.Data;

import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Data
public class DriverResponse extends BaseDriver {

    @Transient
    private String fullName;

    private List<CarResponse> cars;

    public DriverResponse(Driver driver){
        this.setLastName(driver.getLastName());
        this.setDriverLicense(driver.getDriverLicense());
        this.setFirstName( driver.getFirstName());
        this.setId(driver.getId());
        this.fullName = driver.getFirstName() + " " + driver.getLastName();
        this.setAddress(driver.getAddress());

        if(driver.getCars() != null) {
            cars = new ArrayList<>();
            for (Car car: driver.getCars()) {
                cars.add(new CarResponse(car));
            }
        }
    }

}
