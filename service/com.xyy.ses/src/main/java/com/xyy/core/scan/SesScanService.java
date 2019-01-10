package com.xyy.core.scan;

import com.xyy.scan.CommonScanService;
import com.xyy.spi.SPI;

@SPI(isDefault=true,value="XYY_CUSTOM_SPI")
public class SesScanService extends CommonScanService {
	@Override
	public String getMybatisScanPath() {
		return "classpath:mappers/*/*.xml";
	} 
}
