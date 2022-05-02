package com.vorkurt.entity.transport.pack;

import com.vorkurt.entity.transport.car.Car;
import com.vorkurt.entity.transport.pack.request.base.Cosignee;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "packs")
@Entity
@Data
@NoArgsConstructor
public class Pack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cosignee_address_id")
    private Cosignee cosignee;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id")
    private Car carId;
}
