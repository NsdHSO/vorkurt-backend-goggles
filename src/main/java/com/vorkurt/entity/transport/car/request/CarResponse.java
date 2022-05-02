package com.vorkurt.entity.transport.car.request;

import com.vorkurt.entity.transport.car.Car;
import com.vorkurt.entity.transport.car.base.CarBase;

public class CarResponse extends CarBase {
    public CarResponse(Car carDAO){
        this.setKgPerWeight(carDAO.getKgPerWeight());
        this.setReservoirFuel(carDAO.getReservoirFuel());
        this.setPlateNumber(carDAO.getPlateNumber());
        this.setId(carDAO.getId());
    }
}
