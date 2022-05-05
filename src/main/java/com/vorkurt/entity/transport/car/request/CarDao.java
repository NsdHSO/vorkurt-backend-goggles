package com.vorkurt.entity.transport.car.request;

import com.vorkurt.entity.transport.car.base.CarBase;
import com.vorkurt.entity.transport.pack.Pack;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

public class CarDao extends CarBase {
    @Column
    @OneToMany(mappedBy= "car",cascade= CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pack> packs;
}
