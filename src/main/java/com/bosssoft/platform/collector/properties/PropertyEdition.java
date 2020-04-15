 package com.bosssoft.platform.collector.properties;

import com.bosssoft.platform.collector.Constants;

/**
  * 版本
  * @author huangxw
  * @date 2020/04/07
  */
 public class PropertyEdition extends SelectableProperty{
     
     
    public String propertyName() {
       
         return Constants.PROPERTY_NAME_EDITION;
    }

   
    public String getErrorMessage() {
        // TODO Auto-generated method stub
         return null;
    }

    @Override
    protected String getOptionTitle() {
        
         return "edition";
    }

    /**
     * 根据选项Key获取选项值
     * 
     * @return
     */
    protected  String getValueByOptionKey(Integer key){
        String value=options.get(key);
        if("PE".equals(value)) return Boolean.FALSE.toString();
        else if("ED".equals(value)) return Boolean.TRUE.toString();
        else return null;
    }

    @Override
    protected void initOptions() {
        options.put(1, "PE");
        options.put(2, "ED");
    }

}
