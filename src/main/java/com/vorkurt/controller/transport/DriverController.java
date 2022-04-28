package com.vorkurt.controller.transport;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.vorkurt.entity.transport.driver.Driver;
import com.vorkurt.entity.transport.driver.request.DriverRequest;
import com.vorkurt.entity.transport.driver.request.DriverResponse;
import com.vorkurt.entity.transport.driver.request.RequstInDriver;
import com.vorkurt.service.transport.DriverService;

@RestController
@RequestMapping("/api/transport/driver")
@CrossOrigin
public class DriverController {
    Logger logger = LoggerFactory.getLogger(DriverController.class);

    @Autowired
    DriverService driverService;

    @GetMapping("{lastName}")
    public List<DriverResponse> getDriverLastName(@PathVariable String lastName) {
        List<Driver> driverDAO = driverService.getDriverLastName(lastName);

        List<DriverResponse> driverResponse = new ArrayList<>();
        driverDAO.stream().forEach(driver -> {
            driverResponse.add(new DriverResponse(driver));
        });
        return driverResponse;
    }

    @PutMapping()
    public Driver updateDriver(@Valid @RequestBody Driver driver) {
        return driverService.updateDriver(driver);
    }

    @GetMapping({"n/{lastName}"})
    public List<DriverResponse> getDriverLastNames(@PathVariable String lastName) {
        return driverService.findAllByLastName(lastName);
    }

    @GetMapping({"a"})
    public List<DriverResponse> getDriverLas(@RequestBody RequstInDriver lastName) {
        return driverService.getLastNameIn(lastName.getLastNames());
    }

    @GetMapping({"getPagination"})
    public List<DriverResponse> getDataWithPagination(@RequestParam int currentPage, @RequestParam int size) {
        return driverService.getDataWithPagination(currentPage, size);
    }

    @GetMapping({"getEndWith"})
    public List<DriverResponse> getDataEndsWith(@RequestParam String stringMatch) {

        return driverService.getAllDriverEndWith(stringMatch);
    }

    @PostMapping()
    public DriverResponse createDriver(@RequestBody DriverRequest driver) {
        Driver driverDAO = driverService.createNewDriver(driver);
        logger.info("Call Service");
        logger.warn(driver.getAddress().getStreet());
        return new DriverResponse(driverDAO);
    }

    @GetMapping("getDriverWithCity")
    public List<DriverResponse> getAllCity(@RequestParam String city) {
        return driverService.getAllWithCity(city);
    }

    @GetMapping
    public List<DriverResponse> getAllDrivers() {
        logger.info("Call Service");
        logger.info("Get all drivers");
        return driverService.getAllDrivers();
    }

    @GetMapping({"findById"})
    public DriverResponse getAllById(@RequestParam long id) {
        return driverService.getById(id);
    }

    @DeleteMapping()
    public String delete(@RequestParam long id) {
        return "Test";
    }
}
