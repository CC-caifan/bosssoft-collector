package com.bosssoft.platform.collector.properties;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bosssoft.platform.collector.Constants;
import com.bosssoft.platform.license.api.Constant;



public class PropertyCpu implements ServerProperty{

	private static Logger log = LoggerFactory.getLogger(PropertyCpu.class);
	
	private String errorMessage;
	
	public String propertyName() {
		
		return Constants.PROPERTY_NAME_CPU;
	}

	public String propertyValue() {
		//System.out.println("start get cpu info.........");
		String cpuCnt="";
		try {
			
			//返回该机器的系统名称
			String os = System.getProperty("os.name").toLowerCase();

			Process process = null;
			if (os.indexOf(Constant.LICENSE_OS_NAME_WINDOWS) >= 0)
				process = Runtime.getRuntime().exec("cmd.exe /c echo %NUMBER_OF_PROCESSORS%");
			else if (os.indexOf(Constant.LICENSE_OS_NAME_AIX) >= 0)
				process = Runtime.getRuntime().exec(Constant.IMPRIMATUR_AIXCmd);
			else if (os.indexOf(Constant.LICENSE_OS_NAME_HP) >= 0)
				process = Runtime.getRuntime().exec(Constant.IMPRIMATUR_HPUXCmd);
			else if (os.indexOf(Constant.LICENSE_OS_NAME_LINUX) >= 0)
				process = Runtime.getRuntime().exec(Constant.IMPRIMATUR_LinuxCmd);
			else if ((os.indexOf(Constant.LICENSE_OS_NAME_SOLARIS) >= 0) || (os.indexOf(Constant.LICENSE_OS_NAME_SUNOS) >= 0)) {
				process = Runtime.getRuntime().exec(Constant.IMPRIMATUR_SolarisCmd);
			}
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			cpuCnt = bufferedReader.readLine().trim();
			//log.info("get cpu info end,cpu count:"+cpuCnt);
		} catch (IOException e) {
			errorMessage="get cpu info fail";
			log.error("get cpu info fail"+e.toString());
			
		} 
		return cpuCnt;
	}

	public String getErrorMessage() {
		
		return errorMessage;
	}

}
