 package com.bosssoft.platform.collector.properties;

import com.bosssoft.platform.collector.Constants;

public class PropertyDB extends SelectableProperty{
    

    public String propertyName() {
        
         return Constants.PROPERTY_NAME_DB;
    }

    public String getErrorMessage() {
        // TODO Auto-generated method stub
         return null;
    }

    @Override
    protected String getOptionTitle() {
        
         return "DB";
    }

    @Override
    protected void initOptions() {
        options.put(1, "ORACLE");
        options.put(2, "MYSQL");
        options.put(3, Constants.UNLIMIT);
         
    }

}
