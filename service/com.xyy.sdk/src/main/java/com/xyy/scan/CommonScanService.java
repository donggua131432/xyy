package com.xyy.scan;

import com.xyy.spi.SPI;

@SPI(isDefault=true,value="common")
public class CommonScanService implements CustomerScanService {

	@Override
	public String getMybatisScanPath() {
		return "classpath:mappers/*.xml";
	} 
}
