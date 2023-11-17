/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pav2py.excel.pts.functions;
import java.util.Map;

import com.pav2py.excel.utlilty.EDataType;
import com.pav2py.excel.utlilty.Util;

/**
 *
 * @author WLH1KOR
 */
public class StrCatFunction extends AbstractFunction {

    public StrCatFunction() {
       keysSet.add("String1"); keysSet.add("String2"); keysSet.add("String3"); keysSet.add("String4");keysSet.add("String5"); keysSet.add("String6");
       keysSet.add("String7"); keysSet.add("String8");keysSet.add("String9"); keysSet.add("String10"); keysSet.add("String11");keysSet.add("String12");
    
    }

    @Override
    public String processParams() {
        //pre process paramaters remove newline,replace ; with , and remove last semi colon
        super.processParams();
        if (super.getOutPutParam() != null && !super.getOutPutParam().isEmpty()) {
            return super.getOutPutParam() + " = " + super.getName() + "(" + "globals(), " + processInputParams() + ")";
        }
        return super.getName() + "(" + "globals(), " + processInputParams() + ")";
    }

    private String processInputParams() {
        StringBuilder builder = new StringBuilder();
        
        String params = getParams();
   
        //get all inputParameters of the function in map
        //this method will process inputParameters string differently, no splitting of string by any chars like "," and "="
        Map<String ,String> processedInputParams = super.processInputParameters(keysSet, params);
        
        for(String key :  processedInputParams.keySet()){
            //builder.append(key).append("=");
            if(Util.validate(processedInputParams.get(key)) != EDataType.INVALID){
                builder.append(processedInputParams.get(key));
            }else{
                 builder.append("\"").append(processedInputParams.get(key)).append("\"");
            }
            builder.append(",");
        }
        String output;
        output = builder.toString().replace("&", "");
        output = "[" + output.substring(0, output.length() - 1) + "]"; //remove last coma
        return output;
    }

    @Override
    public String toPy() {
        return processParams();
    }

}
