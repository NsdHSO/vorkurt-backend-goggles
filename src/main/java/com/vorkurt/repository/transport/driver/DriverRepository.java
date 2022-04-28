package com.vorkurt.repository.transport.driver;

import com.vorkurt.entity.transport.driver.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    List<Driver> findAllByLastName(String lastName);

    List<Driver> findByLastName(String lastName);

    List<Driver> findByLastNameIn(List<String> lastNames);

    List<Driver> findByLastNameEndsWith(String lastName);

    List<Driver> findByAddress_City(String city);
}
