package com.vorkurt.repository.transport.pack;

import com.vorkurt.entity.transport.car.Car;
import com.vorkurt.entity.transport.pack.Pack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PackRepository extends PagingAndSortingRepository<Pack, Long> {

    List<Pack> findAllByCarId(Car car);
}
