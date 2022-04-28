package com.vorkurt.service.address;

import com.vorkurt.repository.address.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;
}
