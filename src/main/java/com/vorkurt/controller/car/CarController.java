package com.vorkurt.controller.car;

import com.vorkurt.service.car.CsvExportService;
import com.vorkurt.service.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("api/transport/car")
public class CarController {

    @Autowired
    CarService carService;

    @Autowired
    CsvExportService csvExportService;

    @RequestMapping(path = "/cars")
    public void getAllCars(HttpServletResponse servletResponse) throws IOException {
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition", "attachment; filename=\"cars.csv\"");
        csvExportService.writeCarsToCsv(servletResponse.getWriter());
    }
}
