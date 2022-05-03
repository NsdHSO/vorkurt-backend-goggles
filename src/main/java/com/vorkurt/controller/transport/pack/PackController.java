package com.vorkurt.controller.transport.pack;

import com.vorkurt.service.pack.PackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/transport/pack")
@CrossOrigin
public class PackController {
    Logger logger = LoggerFactory.getLogger(PackController.class);

    @Autowired
    PackService packService;
}
