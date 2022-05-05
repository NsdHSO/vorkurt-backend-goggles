package com.vorkurt.entity.transport.pack;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.vorkurt.entity.transport.car.Car;
import com.vorkurt.entity.transport.pack.format.FormatPck;
import com.vorkurt.entity.transport.pack.refound.RefoundType;
import com.vorkurt.entity.transport.pack.request.DescriptionImplementation;
import com.vorkurt.entity.transport.pack.request.PackAddress;
import com.vorkurt.entity.transport.pack.request.base.Consignee;
import lombok.*;

import javax.persistence.*;

@Table(name = "packs")
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Getter
@Setter
@NoArgsConstructor
@ToString()
public class Pack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "cosignee_address_id")
    private Consignee consignee;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    @JsonBackReference
    private Car car;

    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "description_package_id")
    private DescriptionImplementation description;

    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "package_address_id")
    private PackAddress packAddress;

    @OneToOne(cascade = {CascadeType.PERSIST})
    private FormatPck typeBox;

    @Column
    private boolean repayment;

    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "refound_type")
    private RefoundType refoundType;

}
