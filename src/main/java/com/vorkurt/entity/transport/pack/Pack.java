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

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    @NonNull
    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "cosignee_address_id")
    private Consignee consignee;

    @NonNull
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    @JsonBackReference
    @ToString.Exclude
    private Car car;
    
    @NotNull
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "description_package_id")
    private DescriptionImplementation description;

    @NonNull
    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "package_address_id")
    private PackAddress packageAddress;
    
    @NonNull
    @OneToOne(cascade = {CascadeType.PERSIST})
    private FormatPck typeBox;

    @NotNull
    @Column
    private boolean repayment;

    @NonNull
    @OneToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "refound_type")
    private RefoundType refoundType;
    
    @NonNull
    @Column
    private String takenDate;
    
    @NonNull
    @Column
    private String finishDate;
    
}
