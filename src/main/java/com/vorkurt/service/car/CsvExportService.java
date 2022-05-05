package com.vorkurt.service.car;

import com.vorkurt.entity.transport.car.Car;
import com.vorkurt.repository.transport.car.CarRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import java.io.Writer;


@Service
public class CsvExportService {

    @Autowired
    CarRepository carRepository;

    public void writeCarsToCsv(Writer writer){
        List<Car> cars = carRepository.findAll();
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            for(Car car: cars){
                csvPrinter.printRecord(car.getId(), car.getPlateNumber(), car.getReservoirFuel());
            }
        }
        catch (IOException e) {
            System.out.println("Error while writing CSV \n" + e);
        }
    }
}
