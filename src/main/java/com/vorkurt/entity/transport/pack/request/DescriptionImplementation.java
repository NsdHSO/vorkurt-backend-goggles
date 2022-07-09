package com.vorkurt.entity.transport.pack.request;

import com.vorkurt.entity.transport.pack.base.DescriptionBase;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name = "description_package")
@Entity
@Data
public class DescriptionImplementation extends DescriptionBase {

    
    @Column
    private String note;

    public DescriptionImplementation(){
        this.note = "";
    }
}
