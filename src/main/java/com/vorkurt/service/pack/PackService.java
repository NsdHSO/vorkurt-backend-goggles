package com.vorkurt.service.pack;

import com.vorkurt.entity.transport.pack.Pack;
import com.vorkurt.entity.transport.pack.response.PackResponse;
import com.vorkurt.repository.transport.pack.PackRepository;
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

    /**
     * Retrieve all packs
     * @return List of type Pack.
     */
    public List<PackResponse> getAllPacks() {
        logger.debug("Get All Pack");
        List<Pack> daoPack = packRepository.findAll();
        List<PackResponse> packResponses = new ArrayList<>();

        daoPack.stream().forEach(pack -> {
            if (!pack.isRepayment()){
                pack.setRepayment(false);
            }
            packResponses.add(new PackResponse(pack));
        });

        return  packResponses;

    }
}
