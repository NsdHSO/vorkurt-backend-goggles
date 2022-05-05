package com.vorkurt.entity.transport.car.request;

import com.vorkurt.entity.transport.car.base.CarBase;
import com.vorkurt.entity.transport.pack.Pack;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CreateCarRequest extends CarBase {

    private List<Pack> packs = new ArrayList<>();
}
