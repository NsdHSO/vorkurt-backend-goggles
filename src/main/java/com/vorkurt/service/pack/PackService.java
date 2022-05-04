package com.vorkurt.service.pack;

import com.vorkurt.entity.transport.pack.Pack;
import com.vorkurt.entity.transport.pack.response.PackResponse;
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
            if (!pack.isRepayment()) {
                pack.setRepayment(false);
            }
            packResponses.add(new PackResponse(pack));
        });

        return packResponses;

    }

    public PackResponse modifiedPack(long id, Pack objectPack) {
        Pack pacDao = this.packRepository.findById(id).get();

        if (objectPack.getPackAddress() != null) {
            pacDao.setPackAddress(objectPack.getPackAddress());
            addressRepository.save(pacDao.getPackAddress());
        }
        if (objectPack.getRefoundType() != null) {
            if (objectPack.getRefoundType().isToRecipient() && pacDao.getRefoundType().isToSender()) {
                pacDao.getRefoundType().setToRecipient(true);
                pacDao.getRefoundType().setToSender(false);
            } else if (!objectPack.getRefoundType().isToRecipient() && pacDao.getRefoundType().isToSender()) {
                pacDao.getRefoundType().setToRecipient(false);
                pacDao.getRefoundType().setToSender(true);

            } else if (!pacDao.getRefoundType().isToSender() &&  !pacDao.getRefoundType().isToRecipient()){
                {
                    pacDao.getRefoundType().setToRecipient(false);
                    pacDao.getRefoundType().setToSender(true);
                }
            } else {
                pacDao.getRefoundType().setToRecipient(false);
                pacDao.getRefoundType().setToSender(true);
            }
            refoundRepository.save(pacDao.getRefoundType());
        }
        if (objectPack.getConsignee() != null) {
            pacDao.setConsignee(objectPack.getConsignee());
        }
        if (objectPack.isRepayment()) {
            pacDao.setRepayment(objectPack.isRepayment());
        }
        if (objectPack.getTypeBox() != null) {
            pacDao.setTypeBox(objectPack.getTypeBox());
            this.formatPckRepository.save(pacDao.getTypeBox());
        }
        if (objectPack.getDescription() != null) {
            pacDao.setDescription(objectPack.getDescription());
            this.descriptionRepository.save(pacDao.getDescription());
        }

        this.packRepository.save(pacDao);

        return new PackResponse(pacDao);

    }
}
