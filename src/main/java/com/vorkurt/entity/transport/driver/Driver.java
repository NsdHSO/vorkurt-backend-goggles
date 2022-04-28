package com.vorkurt.entity.transport.driver;

import com.vorkurt.entity.transport.car.Car;
import com.vorkurt.entity.transport.driver.request.DriverRequest;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "drivers")
@EnableAutoConfiguration
public class Driver extends BaseDriver {


    @Column
    @OneToMany(mappedBy = "driver")
    private List<Car> cars;

    public Driver(DriverRequest driverReq) {
        this.setDriverLicense(driverReq.getDriverLicense());
        this.setFirstName(driverReq.getFirstName());
        this.setLastName(driverReq.getLastName());
        this.setAddress(driverReq.getAddress());
    }
}
