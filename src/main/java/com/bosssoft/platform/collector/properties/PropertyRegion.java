 package com.bosssoft.platform.collector.properties;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bosssoft.platform.collector.Constants;

public class PropertyRegion  implements ServerProperty{
    //获取日志
    private static Logger log = LoggerFactory.getLogger(PropertyRegion.class);
    
    protected  String errorMessage;
    
    public String propertyName() {
       
         return Constants.PROPERTY_NAME_REGION;
    }

    public String propertyValue() {
        StringBuffer prompt =
            new StringBuffer("Please input ").append("region: ");

        System.out.println(prompt);
        
        String region=null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            region=br.readLine();
        } catch (IOException e) {
            errorMessage = "read region info from console error";
            log.error("read region info from console error " + e.toString());
        }
        
         return region;
    }

    public String getErrorMessage() {
        // TODO Auto-generated method stub
         return null;
    }

}
