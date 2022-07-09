package com.vorkurt.entity.transport.pack.response;

import java.util.List;

import com.vorkurt.entity.transport.pack.Pack;

import lombok.Data;


@Data
public class PackResponseData {
	
	private List<PackResponse> entries ;
	private int countEntries;
	
	public PackResponseData() {

	}


}