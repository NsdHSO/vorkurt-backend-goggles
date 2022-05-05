package com.vorkurt.entity.transport.car.request;

import com.vorkurt.entity.transport.car.Car;
import com.vorkurt.entity.transport.car.base.CarBase;
import com.vorkurt.entity.transport.pack.Pack;
import com.vorkurt.entity.transport.pack.response.PackResponse;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class CarResponse extends CarBase {

    List<PackResponse> packages;


    public CarResponse(Car carDAO){
        this.setKgPerWeight(carDAO.getKgPerWeight());
        this.setReservoirFuel(carDAO.getReservoirFuel());
        this.setPlateNumber(carDAO.getPlateNumber());
        this.setId(carDAO.getId());
        this.setNumberPacks(carDAO.getNumberPacks());
        if(carDAO.getPacks() != null){
            packages = new ArrayList<>();
            for (Pack pack: carDAO.getPacks()){
                packages.add(new PackResponse(pack));
            }
        }
    }
}
