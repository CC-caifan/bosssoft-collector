 package com.bosssoft.platform.collector.properties;

import com.bosssoft.platform.collector.Constants;

public class PropertyServer extends SelectableProperty{

    public String propertyName() {
         return "as";
    }

   

    public String getErrorMessage() {
        // TODO Auto-generated method stub
         return null;
    }



    @Override
    protected void initOptions() {
        options.put(1, "tomcat");
        options.put(2, "weblogic");
        options.put(3, Constants.UNLIMIT);
         
    }



    @Override
    protected String getOptionTitle() {
       
         return "server";
    }

}
