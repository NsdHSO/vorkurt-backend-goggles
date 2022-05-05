package com.vorkurt.service.pack;

import com.vorkurt.entity.transport.car.Car;
import com.vorkurt.entity.transport.pack.Pack;
import com.vorkurt.entity.transport.pack.response.PackResponse;
import com.vorkurt.repository.transport.car.CarRepository;
import com.vorkurt.repository.transport.pack.PackRepository;
import com.vorkurt.repository.transport.pack.address.PackageAddressRepository;
import com.vorkurt.repository.transport.pack.description.DescriptionRepository;
import com.vorkurt.repository.transport.pack.format.FormatPckRepository;
import com.vorkurt.repository.transport.pack.refound.RefoundRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PackService {

    Logger logger = LoggerFactory.getLogger(PackService.class);

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

    @Autowired
    PackageAddressRepository addressRepository;

    /**
     * Retrieve all packs
     *
     * @return List of type Pack.
     */
    public List<PackResponse> getAllPacks() {
        logger.debug("Get All Pack");
        List<Pack> daoPack = packRepository.findAll();
        List<PackResponse> packResponses = new ArrayList<>();

        daoPack.stream().forEach(pack -> {
            _setIsRepayment(!pack.isRepayment(), false, pack);
            packResponses.add(new PackResponse(pack));
        });

        return packResponses;

    }

    public List<PackResponse> getPacksByCarId(Long carId) {
        Car car = new Car();
        car.setId(carId);
        List<Pack> daoPacks = packRepository.findAllByCarId(car);

        return daoPacks.stream()
                .peek(pack -> {
                    _setIsRepayment(!pack.isRepayment(), false, pack);
                })
                .map(PackResponse::new)
                .collect(Collectors.toList());
    }

    /***
     *@param id
     *@param objectPack
     * Return object from Dao
     */
    public PackResponse modifiedPack(long id, Pack objectPack) {
        Pack pacDao = this.packRepository.findById(id).get();

        _setPropertiesOnPackDao(objectPack, pacDao);

        this.packRepository.save(pacDao);

        return new PackResponse(pacDao);

    }

    /***
     *@param objectPack is from request
     *@param pacDao from database
     * not return nothing but set object properties in pacDao
     */
    private void _setPropertiesOnPackDao(Pack objectPack, Pack pacDao) {
        _setPackAddress(objectPack, pacDao);
        _setRefoundType(objectPack, pacDao);
        _setConsignee(objectPack, pacDao);
        _setIsRepayment(objectPack.isRepayment(), objectPack.isRepayment(), pacDao);
        _setgetTypeBox(objectPack, pacDao);
        _setDescription(objectPack, pacDao);
    }

    /***
     *@param objectPack is from request
     *@param pacDao from database
     *
     */
    private void _setPackAddress(Pack objectPack, Pack pacDao) {
        if (objectPack.getPackAddress() != null) {
            pacDao.setPackAddress(objectPack.getPackAddress());
        }
    }

    /***
     *@param objectPack is from request
     *@param pacDao from database
     */
    private void _setDescription(Pack objectPack, Pack pacDao) {
        if (objectPack.getDescription() != null) {
            pacDao.setDescription(objectPack.getDescription());
        }
    }

    /***
     *@param objectPack is from request
     *@param pacDao from database
     */
    private void _setgetTypeBox(Pack objectPack, Pack pacDao) {
        if (objectPack.getTypeBox() != null) {
            pacDao.setTypeBox(objectPack.getTypeBox());
        }
    }

    /***
     *@param objectPack is from request
     *@param pacDao from database
     */
    private void _setIsRepayment(boolean objectPack, boolean objectPack1, Pack pacDao) {
        if (objectPack) {
            pacDao.setRepayment(objectPack1);
        }
    }

    /***
     *@param objectPack is from request
     *@param pacDao from database
     */
    private void _setConsignee(Pack objectPack, Pack pacDao) {
        if (objectPack.getConsignee() != null) {
            pacDao.setConsignee(objectPack.getConsignee());
        }
    }

    /***
     *@param objectPack is from request
     *@param pacDao from database
     */
    private void _setRefoundType(Pack objectPack, Pack pacDao) {
        if (objectPack.getRefoundType() != null) {
            if (objectPack.getRefoundType().isToRecipient() && pacDao.getRefoundType().isToSender()) {
                pacDao.getRefoundType().setToRecipient(true);
                pacDao.getRefoundType().setToSender(false);
            } else if (!objectPack.getRefoundType().isToRecipient() && pacDao.getRefoundType().isToSender()) {
                pacDao.getRefoundType().setToRecipient(false);
                pacDao.getRefoundType().setToSender(true);

            } else if (!pacDao.getRefoundType().isToSender() && !pacDao.getRefoundType().isToRecipient()) {
                {
                    pacDao.getRefoundType().setToRecipient(false);
                    pacDao.getRefoundType().setToSender(true);
                }
            } else {
                pacDao.getRefoundType().setToRecipient(false);
                pacDao.getRefoundType().setToSender(true);
            }
        }
    }
}
