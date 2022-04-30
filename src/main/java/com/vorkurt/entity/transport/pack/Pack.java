package com.vorkurt.entity.transport.pack;

import com.vorkurt.entity.transport.pack.tables.PackAddress;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;

@Entity
@Data
@Table(name = "packages")
@NoArgsConstructor
@EnableAutoConfiguration
public class Pack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne()
    @JoinColumn(name = "pack_id")
    private PackAddress packAddress;

}
