package com.vorkurt.entity.transport.pack.refound;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "refound_type")
@NoArgsConstructor
@Data
public class RefoundType {

	@Id
	@GeneratedValue
	private long id;

	@Column
	private boolean toRecipient;

	@Column
	boolean toSender;
}
