package com.vorkurt.controller.transport.pack;

import com.vorkurt.entity.transport.pack.Pack;
import com.vorkurt.entity.transport.pack.response.PackResponse;
import com.vorkurt.service.pack.PackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.RequestPath;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/transport/pack")
@CrossOrigin
public class PackController {
    Logger logger = LoggerFactory.getLogger(PackController.class);

    @Autowired
    PackService packService;

    @GetMapping()
    public List<PackResponse> getAllPacks(){
        logger.debug("Method get all packs");
        return this.packService.getAllPacks();
    }

    @PutMapping("{id}")
    public PackResponse modifiedPack(@PathVariable long id, @RequestBody Pack objModified){
        logger.debug("Method modified pack");
        return this.packService.modifiedPack(id, objModified);
    }
}
