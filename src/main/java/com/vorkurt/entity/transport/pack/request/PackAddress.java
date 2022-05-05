package com.vorkurt.entity.transport.pack.request;

import com.vorkurt.entity.address.base.BaseAddress;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "package_address")
@Data
public class PackAddress extends BaseAddress {


}
