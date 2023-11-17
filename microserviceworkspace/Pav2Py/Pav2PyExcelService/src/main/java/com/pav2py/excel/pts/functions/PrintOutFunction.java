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
public class PrintOutFunction extends AbstractFunction {

    public PrintOutFunction() {
        keysSet.add("Transfer_Value");
        keysSet.add("Style_Format");
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
        Map<String, String> processedInputParams = super.processInputParameters(keysSet, params);

        for (String key : processedInputParams.keySet()) {
            builder.append(key).append("=");
            if (Util.validate(processedInputParams.get(key)) != EDataType.INVALID || Util.isPredefinedConstant(processedInputParams.get(key))) {
                if(!Util.checkForG_NERRAndReadItWithGlobals(processedInputParams.get(key)).isEmpty())
                    builder.append(Util.checkForG_NERRAndReadItWithGlobals(processedInputParams.get(key)));
                else
                    builder.append(processedInputParams.get(key));
            } else {
                builder.append("\"").append(processedInputParams.get(key)).append("\"");
            }
            builder.append(",");
        }
        String out=builder.toString().replace("&", "");
        out = out.substring(0,out.length()-1); //remove last comma
        
        return out;
    }
    
    @Override
    public String toPy() {
        return processParams();
    }

}
