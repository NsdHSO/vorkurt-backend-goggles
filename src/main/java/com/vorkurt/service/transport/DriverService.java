package com.vorkurt.service.transport;

import com.core.exception.EntityNotFound;
import com.vorkurt.entity.address.Address;
import com.vorkurt.entity.transport.car.request.CreateCarRequest;
import com.vorkurt.entity.transport.pack.Pack;
import com.vorkurt.entity.transport.pack.format.FormatPck;
import com.vorkurt.entity.transport.pack.refound.RefoundType;
import com.vorkurt.entity.transport.pack.request.DescriptionImplementation;
import com.vorkurt.entity.transport.pack.request.PackAddress;
import com.vorkurt.entity.transport.pack.request.base.Cosignee;
import com.vorkurt.repository.address.AddressRepository;
import com.vorkurt.entity.transport.car.Car;
import com.vorkurt.entity.transport.driver.Driver;
import com.vorkurt.entity.transport.driver.request.DriverRequest;
import com.vorkurt.entity.transport.driver.request.DriverResponse;
import com.vorkurt.repository.transport.car.CarRepository;
import com.vorkurt.repository.transport.driver.DriverRepository;
import com.vorkurt.repository.transport.pack.PackRepository;
import com.vorkurt.repository.transport.pack.address.PackageAddressRepository;
import com.vorkurt.repository.transport.pack.description.DescriptionRepository;
import com.vorkurt.repository.transport.pack.format.FormatPckRepository;
import com.vorkurt.repository.transport.pack.refound.RefoundRepository;
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

    @Autowired
    PackRepository packRepository;

    @Autowired
    DescriptionRepository descriptionRepository;

    @Autowired
    PackageAddressRepository packageAddressRepository;

    @Autowired
    FormatPckRepository formatPckRepository;

    @Autowired
    RefoundRepository refoundRepository;

    /**
     * Added new driver with packages and returned.
     * @param request need at Driver Request
     * @return new driver .
     */
    public Driver createNewDriver(DriverRequest request) {
        Driver newDriver = new Driver(request);
        Address newDriverAddress = new Address();
        List<Car> newCar = new ArrayList<>();
        List<Pack> newPack;
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
                car.setPlateNumber(carRequest.getPlateNumber());
                car.setReservoirFuel(carRequest.getReservoirFuel());
                car.setDriver(newDriver);
                newPack = new ArrayList<>();

                // Verified if user put the packs
                if (carRequest.getPacks() != null) {
                    for (Pack pack : carRequest.getPacks()) {

                        // Verified if user put the consignee
                        if(pack.getConsignee() !=null) {
                            pack.setConsignee(
                                    new Cosignee(pack.getConsignee().getApartment(),
                                            pack.getConsignee().getStreet(),
                                            pack.getConsignee().getCity(),
                                            pack.getConsignee().getNumber(),
                                            pack.getConsignee().getPostalCode()));
                        }else{
                            pack.setConsignee(new Cosignee());
                        }
//                        pack.setCarId(car);

                        // Verified if user put description
                        if (pack.getDescription() != null) {
                            pack.setDescription(pack.getDescription());
                        } else {
                            pack.setDescription(new DescriptionImplementation());
                        }

                        // Verified if user put the type of pack
                        if(pack.getTypeBox() != null){
                            FormatPck auxFrmt = new FormatPck();
                            auxFrmt.setTypePck(pack.getTypeBox().getTypePck());

                            // Verified if user put the type of pack at box
                            if(pack.getTypeBox().isBox()){
                                auxFrmt.setBox(true);
                                auxFrmt.setEnvelop(false);
                            }
                            // Verified if user put the type of pack at envelop type
                            else  if(pack.getTypeBox().isEnvelop()){
                                auxFrmt.setBox(false);
                                auxFrmt.setEnvelop(true);
                            }
                            pack.setTypeBox(auxFrmt);
                        }else{
                            pack.setTypeBox(new FormatPck());
                        }

                        // Verified is true repayment
                        if(pack.isRepayment()){
                            pack.setRepayment(pack.isRepayment());
                        }
                        else{
                            pack.setRepayment(false);
                        }

                        // Verified exists address
                        if(pack.getPackAddress() !=null){
                            pack.setPackAddress(pack.getPackAddress());
                        }else{
                            pack.setPackAddress(new PackAddress());
                        }

                        if(pack.getRefoundType()!=null){
                            RefoundType refoundType = new RefoundType();
                            refoundType.setToSender(true);
                            if(pack.getRefoundType().isToRecipient()){
                                refoundType.setToRecipient(true);
                                refoundType.setToSender(false);
                            }
                            pack.setRefoundType(refoundType);
                            this.refoundRepository.save(refoundType);
                        }

                        // Saved in DB
                        this.formatPckRepository.save(pack.getTypeBox());
                        this.descriptionRepository.save(pack.getDescription());
                        this.packageAddressRepository.save(pack.getPackAddress());
                        this.packRepository.save(pack);
                        newPack.add(pack);

                    }
                }
                car.setPacks(newPack);
                newCar.add(car);
                newDriver.addCars(car);
            }
            carRepository.saveAll(newCar);
        }
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
