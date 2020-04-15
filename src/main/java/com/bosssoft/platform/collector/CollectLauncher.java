package com.bosssoft.platform.collector;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.bosssoft.platform.collector.properties.PropertyCpu;
import com.bosssoft.platform.collector.properties.PropertyDB;
import com.bosssoft.platform.collector.properties.PropertyEdition;
import com.bosssoft.platform.collector.properties.PropertyIP;
import com.bosssoft.platform.collector.properties.PropertyMAC;
import com.bosssoft.platform.collector.properties.PropertyOS;
import com.bosssoft.platform.collector.properties.PropertyRegion;
import com.bosssoft.platform.collector.properties.PropertyServer;
import com.bosssoft.platform.collector.properties.ServerProperty;
import com.bosssoft.platform.collector.util.CollectorUtils;
import com.bosssoft.platform.license.impl.LicenseUtil;

public class CollectLauncher {
	
	private static Logger log = LoggerFactory.getLogger(CollectLauncher.class);
	
	private static List<ServerProperty> serverProperties=new ArrayList<ServerProperty>();
	
	private static Map<String,Object> serverPropertyValues=new HashMap<String, Object>();
	
	public static void main(String[] args) {
		log.info("is collecting server information........");
		initServerProperties();
		
		boolean collectResult=collectServerPropertyValues();
		
		if(collectResult){
		  String editionValue= (String)serverPropertyValues.get(Constants.PROPERTY_NAME_EDITION);
		  if(Boolean.TRUE.toString().equals(editionValue)){
		      //试用版,设置过期时间 
		      String expiration=CollectorUtils.getAfterMonth(CollectorUtils.getCurrentTimeStr(), 2);
		      serverPropertyValues.put(Constants.PROPERTY_NAME_EXPIR, expiration);
		  }
		    
		    
		  File file=	createServerPropertyFile();
		  log.info("bosssoft-collector execution success and create server-info file on "+file.getPath());
		}
		

	}
	
	private static File createServerPropertyFile() {
		String filePath=getCurrentDirectory()+File.separator+"bosssoft-serverinfo.xml";
		File file=new File(filePath);
		
		String jsonStr=JSON.toJSONString(serverPropertyValues);
		String encryStr=LicenseUtil.encry(jsonStr);
		try{
			FileUtils.writeStringToFile(file, encryStr, "UTF-8", false);
		}catch (Exception e) {
			log.error(e.toString());
			log.error("bosssoft-collector execution failure! because create ServerProperty File fail");
		}
		return file;
		
	}

	private static String getCurrentDirectory(){
		File currentDirectory	= new File(".");
		String currentHome=null;
		try {
			File rootFile=currentDirectory.getCanonicalFile().getParentFile();
			if(rootFile!=null)
				currentHome=currentDirectory.getCanonicalFile().getParentFile().getCanonicalPath();
			else
				currentHome=currentDirectory.getCanonicalFile().getCanonicalPath();
		}catch(Exception exp){
			exp.printStackTrace();
		}
		return currentHome;
	}
	
	private static boolean collectServerPropertyValues(){
		for (ServerProperty serverProperty : serverProperties) {
			String value=serverProperty.propertyValue();
			if(StringUtils.isNotEmpty(serverProperty.getErrorMessage())){
				log.error("bosssoft-collector execution failure! because "+serverProperty.getErrorMessage());
				return false;
			}else{
				serverPropertyValues.put(serverProperty.propertyName(), value);
			}
		}
		return true;
		
	}

	private static void initServerProperties() {
		serverProperties.add(new PropertyCpu());
		serverProperties.add(new PropertyIP());
		serverProperties.add(new PropertyMAC());
		serverProperties.add(new PropertyOS());
		
		serverProperties.add(new PropertyEdition());
		serverProperties.add(new PropertyDB());
		serverProperties.add(new PropertyServer());
		serverProperties.add(new PropertyRegion());
		
	}

	

}
