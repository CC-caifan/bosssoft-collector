package com.bosssoft.platform.collector.properties;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class SelectableProperty implements ServerProperty {
    
    private static Logger log = LoggerFactory.getLogger(SelectableProperty.class);
    
    protected  Map<Integer,String> options=new HashMap<Integer, String>();
    
    protected  String errorMessage;
    
    public SelectableProperty(){
        initOptions();
    }

    protected abstract void initOptions() ;

    public String propertyValue() {
        String value="";
        try{
            Integer optionKey = readOptionKeyFromConsole();
            value = getValueByOptionKey(optionKey);
            while (StringUtils.isEmpty(value)) {
                System.out.println("error input!");
                optionKey = readOptionKeyFromConsole();
                value = getValueByOptionKey(optionKey);
            }
        }catch(Exception e){
            String errorinfo=String.format("read %s option choose from console error", getOptionTitle());
            errorMessage = errorinfo;
            log.error(errorinfo + e.toString());
        }
        return value;

    }

    private Integer readOptionKeyFromConsole() throws IOException {
        String input=null;
        
        do {
            System.out.println(getPromptInfo());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             input=br.readLine();
        } while (StringUtils.isEmpty(input)||!StringUtils.isNumeric(input));

        return Integer.parseInt(input);
    }

    /**
     * 获取提示信息
     */
    private String getPromptInfo() {
        StringBuffer promptBuffer =
            new StringBuffer("Please choose ").append(getOptionTitle()).append(System.lineSeparator());
        for (Entry<Integer, String> entry : getOptions().entrySet()) {
            promptBuffer.append(entry.getKey() + ".");
            promptBuffer.append(entry.getValue());
            promptBuffer.append(System.lineSeparator());
        }

        promptBuffer.append("input the options number:");
        return promptBuffer.toString();
    }

    /**
     * 获取所有可选项
     * 
     * @return
     */
    protected  Map<Integer, String> getOptions(){
        return options;
    }

    /**
     * 根据选项Key获取选项值
     * 
     * @return
     */
    protected  String getValueByOptionKey(Integer key){
        return options.get(key);
    }

    /**
     * 选项的标题提示
     * 
     * @return
     */
    protected abstract String getOptionTitle();
}
