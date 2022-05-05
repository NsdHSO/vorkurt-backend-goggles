package com.vorkurt.entity.transport.pack.format;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "format_pck")
@Data
@NoArgsConstructor
public class FormatPck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private boolean envelop;

    @Column
    private boolean box;

    @Enumerated(EnumType.ORDINAL)
    private TypePck typePck;
}
