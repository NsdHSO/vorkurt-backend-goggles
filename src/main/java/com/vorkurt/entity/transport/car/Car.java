package com.vorkurt.entity.transport.car;

import com.fasterxml.jackson.annotation.*;
import com.vorkurt.entity.transport.car.base.CarBase;
import com.vorkurt.entity.transport.driver.Driver;
import com.vorkurt.entity.transport.pack.Pack;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@NoArgsConstructor
@Table(name = "cars")
@Getter
@Setter
@ToString
public class Car extends CarBase {

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @Column
    @OneToMany(mappedBy="car", fetch=FetchType.EAGER, cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JsonManagedReference
    @JsonIgnore
    private List<Pack> packs;


    public Car (Car car){
        this.setReservoirFuel(car.getReservoirFuel());
        this.setPlateNumber(car.getPlateNumber());
        this.setDriver(car.getDriver());
        this.setKgPerWeight(car.getKgPerWeight());
        this.setPacks(car.getPacks());

    }

    public void  addPacks(Pack pack){
        this.packs.add(pack);
    }
}
