package com.vorkurt.service.car;

import com.vorkurt.repository.transport.car.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {
    @Autowired
    CarRepository carRepository;

}
