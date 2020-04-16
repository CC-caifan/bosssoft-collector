package com.bosssoft.platform.collector.properties;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bosssoft.platform.collector.Constants;




public class PropertyOS implements ServerProperty{
	//获取日志
	private static Logger log = LoggerFactory.getLogger(PropertyOS.class);

	private String errorMessage;
	
	public String propertyName() {
		
		return Constants.PROPERTY_NAME_OS;
	}

	public String propertyValue() {
		//System.out.println("start get os info.........");
		String os = System.getProperty("os.name").toUpperCase();
		String osValue = null;
		if (os.startsWith("WINDOWS"))
			osValue = "windows";
		else if (os.indexOf("LINUX") >= 0)
			osValue ="Linux";
		else if (os.indexOf("AIX") >= 0)
			osValue ="aix";
		else if (os.indexOf("HP-UX") >= 0)
			osValue ="hp";
		else if (os.indexOf("SOLARIS") >= 0) {
			osValue ="solaris";
		}
		
		if(StringUtils.isEmpty(osValue)){
			errorMessage="get os info fail";
		}
		
		//log.info("get os info end,os:"+osValue);
		return osValue;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
