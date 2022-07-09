package com.vorkurt.service.transport;

import com.vorkurt.entity.address.Address;
import com.vorkurt.entity.transport.car.request.CreateCarRequest;
import com.vorkurt.entity.transport.pack.Pack;
import com.vorkurt.entity.transport.pack.format.FormatPck;
import com.vorkurt.entity.transport.pack.refound.RefoundType;
import com.vorkurt.entity.transport.pack.request.DescriptionImplementation;
import com.vorkurt.entity.transport.pack.request.PackAddress;
import com.vorkurt.entity.transport.pack.request.base.Consignee;
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
                Car car = _instantiateCar(newDriver, carRequest);
                newPack = new ArrayList<>();

                // Verified if user put the packs
                _setPackOfCar(newPack, carRequest, car);
                car.setNumberPacks(newPack.size());
                newCar.add(car);
                car.setPacks(newPack);
            }
            newDriver.addCars(newCar);
            carRepository.saveAll(newCar);
        }
        return newDriver;
    }

    private void _setPackOfCar(List<Pack> newPack, CreateCarRequest carRequest, Car car) {
        if (carRequest.getPacks() != null) {
            for (Pack pack : carRequest.getPacks()) {

                _setAndVerifiedPackRequest(pack);
                pack.setCar(car);
                newPack.add(pack);
            }
        }
    }

    private Car _instantiateCar(Driver newDriver, CreateCarRequest carRequest) {
        Car car = new Car();
        car.setKgPerWeight(carRequest.getKgPerWeight());
        car.setPlateNumber(carRequest.getPlateNumber());
        car.setReservoirFuel(carRequest.getReservoirFuel());
        car.setDriver(newDriver);
        return car;
    }

    private void _setAndVerifiedPackRequest(Pack pack) {
        // Verified if user put the consignee
        _setConsignee(pack);

        // Verified if user put description
        _veriedDescription(pack);

        // Verified if user put the type of pack
        _setTypeBox(pack);

        // Verified is true repayment
        _setRepayment(pack);

        // Verified exists address
        _setAddress(pack);

        _setRefoundType(pack);
    }

    private void _setTypeBox(Pack pack) {
        if(pack.getTypeBox() != null){
            _setTypePack(pack);
        }else{
            pack.setTypeBox(new FormatPck());
        }
    }

    private void _setRefoundType(Pack pack) {
        if(pack.getRefoundType()!=null){
            RefoundType refoundType = new RefoundType();
            refoundType.setToSender(true);
            if(pack.getRefoundType().isToRecipient()){
                refoundType.setToRecipient(true);
                refoundType.setToSender(false);
            }
            pack.setRefoundType(refoundType);
            this.refoundRepository.save(refoundType);
        }else{
            RefoundType refoundType = new RefoundType();
            pack.setRefoundType(refoundType);
            this.refoundRepository.save(refoundType);
        }
    }

    private void _setAddress(Pack pack) {
        if(pack.getPackageAddress() !=null){
            pack.setPackageAddress(pack.getPackageAddress());
        }else{
            pack.setPackageAddress(new PackAddress());
        }
    }

    private void _setRepayment(Pack pack) {
        if(pack.isRepayment()){
            pack.setRepayment(pack.isRepayment());
        }
        else{
            pack.setRepayment(false);
        }
    }

    private void _setTypePack(Pack pack) {
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
    }

    private void _veriedDescription(Pack pack) {
        if (pack.getDescription() != null) {
            pack.setDescription(pack.getDescription());
        } else {
            pack.setDescription(new DescriptionImplementation());
        }
    }

    private void _setConsignee(Pack pack) {
        if(pack.getConsignee() !=null) {
            pack.setConsignee(
                    new Consignee(pack.getConsignee().getApartment(),
                            pack.getConsignee().getStreet(),
                            pack.getConsignee().getCity(),
                            pack.getConsignee().getNumber(),
                            pack.getConsignee().getPostalCode()));
        }else{
            pack.setConsignee(new Consignee());
        }
    }

    public List<Driver> getDriverLastName(String number) {
        return driverRepository.findAllByLastName(number);
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

        if(based.getCars() != null){
            driver.setCars(based.getCars());
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
