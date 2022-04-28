package com.vorkurt.service.transport;

import com.core.exception.EntityNotFound;
import com.vorkurt.entity.address.Address;
import com.vorkurt.entity.transport.car.request.CreateCarRequest;
import com.vorkurt.repository.address.AddressRepository;
import com.vorkurt.entity.transport.car.Car;
import com.vorkurt.entity.transport.driver.Driver;
import com.vorkurt.entity.transport.driver.request.DriverRequest;
import com.vorkurt.entity.transport.driver.request.DriverResponse;
import com.vorkurt.repository.transport.car.CarRepository;
import com.vorkurt.repository.transport.driver.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class DriverService {

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    CarRepository carRepository;

    public Driver createNewDriver(DriverRequest request) {
        Driver newDriver = new Driver(request);
        Address newDriverAddress = new Address();
        List<Car> newCar = new ArrayList<>();

        newDriverAddress.setStreet(request.getAddress().getStreet());
        newDriverAddress.setCity(request.getAddress().getCity());

        addressRepository.save(newDriverAddress);

        newDriver.setAddress(newDriverAddress);
        driverRepository.save(newDriver);
        if (request.getCarCargo() != null) {
            for (CreateCarRequest carRequest : request.getCarCargo()
            ) {
                Car car = new Car();
                car.setKgPerWeight(carRequest.getKgPerWeight());
                car.setPalateNumber(carRequest.getPalateNumber());
                car.setReservoirFuel(carRequest.getReservoirFuel());
                car.setDriver(newDriver);
                newCar.add(car);
            }

            carRepository.saveAll(newCar);
        }

        newDriver.setCars(newCar);


        return newDriver;
    }

    public List<Driver> getDriverLastName(String number) {
//        return driverRepository.findAllByLastName(number);
        throw new EntityNotFound("Test ");
    }


    public Driver updateDriver(Driver based) {
        Driver driver = driverRepository.findById(based.getId()).get();

        if (based.getDriverLicense() != null) {
            driver.setDriverLicense(based.getDriverLicense());
        }
        if (based.getFirstName() != null) {
            driver.setFirstName(based.getFirstName());
        }
        if (based.getLastName() != null) {
            driver.setLastName(based.getLastName());
        }

        this.driverRepository.save(driver);
        return driver;
    }

    public List<DriverResponse> findAllByLastName(String lastName) {
        List<Driver> driverDAO = this.driverRepository.findByLastName(lastName);
        List<DriverResponse> driverResponse = new ArrayList<>();

        driverDAO.stream().forEach(driver -> {
            driverResponse.add(new DriverResponse(driver));
        });

        return driverResponse;
    }

    public List<DriverResponse> getLastNameIn(List<String> lastNames) {
        List<Driver> driversDAO =
                driverRepository.findByLastNameIn(lastNames);

        List<DriverResponse> driverResponse = new ArrayList<>();

        driversDAO.forEach(driver -> {
            driverResponse.add(new DriverResponse(driver));
        });


        return driverResponse;
    }

    public List<DriverResponse> getDataWithPagination(int currentPage, int size) {
        Pageable pagination = PageRequest.of(currentPage, size);
        List<Driver> driverDAO = driverRepository.findAll(pagination).getContent();
        List<DriverResponse> driverResponse = new ArrayList<>();

        driverDAO.stream().forEach(driver -> {
            driverResponse.add(new DriverResponse(driver));
        });

        return driverResponse;
    }

    public List<DriverResponse> getAllDriverEndWith(String match) {
        List<Driver> driverDAO = driverRepository.findByLastNameEndsWith(match);
        List<DriverResponse> driverResponse = new ArrayList<>();

        driverDAO.stream().forEach(driver -> {
            driverResponse.add(new DriverResponse(driver));
        });

        return driverResponse;

    }

    public List<DriverResponse> getAllDrivers() {
        List<Driver> driverDAO = driverRepository.findAll();
        List<DriverResponse> driverResponse = new ArrayList<>();

        driverDAO.stream().forEach(driver -> {
            driverResponse.add(new DriverResponse(driver));
        });

        return driverResponse;
    }

    @GetMapping
    public List<DriverResponse> getAllWithCity(String city) {
        List<Driver> driverDAO = driverRepository.findByAddress_City(city);
        List<DriverResponse> driverResponse = new ArrayList<>();

        driverDAO.stream().forEach(driver -> {
            driverResponse.add(new DriverResponse(driver));
        });

        return driverResponse;
    }

    public DriverResponse getById(long id) {
        Driver driver = driverRepository.getById(id);
        DriverResponse newDriver = new DriverResponse(driver);
        return newDriver;
    }

}
