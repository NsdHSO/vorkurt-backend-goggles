package com.vorkurt.entity.transport.driver.request;

import com.vorkurt.entity.transport.car.request.CreateCarRequest;
import com.vorkurt.entity.transport.driver.BaseDriver;
import lombok.Data;


import java.util.List;

@Data
public class DriverRequest extends BaseDriver {

    private List<CreateCarRequest> carCargo;

}
