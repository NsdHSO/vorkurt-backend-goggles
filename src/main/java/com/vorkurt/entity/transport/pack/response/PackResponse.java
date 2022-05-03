package com.vorkurt.entity.transport.pack.response;

import com.vorkurt.entity.transport.pack.Pack;
import com.vorkurt.entity.transport.pack.format.FormatPck;
import com.vorkurt.entity.transport.pack.request.PackAddress;
import com.vorkurt.entity.transport.pack.request.base.Cosignee;
import lombok.Data;

@Data
public class PackResponse {

    private Cosignee consignee;

    private String note;

    private FormatPck typeBox;

    private PackAddress packageAddress;

    private boolean repayment;
    public PackResponse(Pack pack){
        this.setConsignee(pack.getConsignee());
        this.setNote(pack.getDescription().getNote());
        this.packageAddress = pack.getPackAddress();
        this.typeBox = pack.getTypeBox();
        this.repayment = pack.isRepayment();
    }

}
