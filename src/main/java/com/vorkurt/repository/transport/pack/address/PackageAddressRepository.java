package com.vorkurt.repository.transport.pack.address;

import com.vorkurt.entity.transport.pack.request.PackAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageAddressRepository extends JpaRepository<PackAddress, Long> {
}
