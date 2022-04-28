package com.vorkurt.entity.address;

import com.vorkurt.entity.address.base.BaseAddress;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name= "driver_address")
public class Address extends BaseAddress {



}
