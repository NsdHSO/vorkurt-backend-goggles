package com.vorkurt.entity.transport.pack.response;

import com.vorkurt.entity.transport.car.Car;
import com.vorkurt.entity.transport.pack.Pack;
import com.vorkurt.entity.transport.pack.format.FormatPck;
import com.vorkurt.entity.transport.pack.refound.RefoundType;
import com.vorkurt.entity.transport.pack.request.PackAddress;
import com.vorkurt.entity.transport.pack.request.base.Consignee;
import lombok.Data;

@Data
public class PackResponse {

	private Long id;

	private Consignee consignee;

	private String note;

	private FormatPck typeBox;

	private RefoundType refoundType;

	private PackAddress packageAddress;

	private String takeDate;

	private boolean repayment;
	
	private String statusPack;

	public PackResponse(Pack pack) {
		setResponse(pack);
	}

	private void setResponse(Pack pack) {
		this.setId(pack.getId());
		this.setConsignee(pack.getConsignee());
		if (pack.getDescription() != null) {
			this.setNote(pack.getDescription().getNote());
		}else {
			this.setNote(" ");
		}
		this.packageAddress = pack.getPackageAddress();
		this.typeBox = pack.getTypeBox();
		this.repayment = pack.isRepayment();
		this.refoundType = pack.getRefoundType();
		this.takeDate = pack.getTakenDate();
		if(pack.getTogglePack() != null)
			this.statusPack = pack.getTogglePack().toString();
	}
}
