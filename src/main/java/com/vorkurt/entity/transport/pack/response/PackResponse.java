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

    private Consignee consignee;

    private String note;

    private FormatPck typeBox;

    private RefoundType refoundType;

    private PackAddress packageAddress;

    private boolean repayment;

    public PackResponse(Pack pack){
        this.setConsignee(pack.getConsignee());
        this.setNote(pack.getDescription().getNote());
        this.packageAddress = pack.getPackAddress();
        this.typeBox = pack.getTypeBox();
        this.repayment = pack.isRepayment();
        this.refoundType = pack.getRefoundType();
    }
}
