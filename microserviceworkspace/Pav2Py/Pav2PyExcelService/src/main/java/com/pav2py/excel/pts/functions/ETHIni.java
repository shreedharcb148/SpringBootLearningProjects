/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pav2py.excel.pts.functions;

import java.util.Map;

/**
 *
 * @author WLH1KOR
 */
public class ETHIni extends AbstractFunction {
     public ETHIni() {
        keysSet.add("Logic_Channel");
        keysSet.add("Mode");
       
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
        
        String params = getParams();

        //get all inputParameters of the function in map
        //this method will process inputParameters string differently, no splitting of string by any chars like "," and "="
        Map<String, String> processedInputParams = super.processInputParameters(keysSet, params);
        String buildParameters = super.buildParameters(processedInputParams);
        StringBuilder builder = new StringBuilder(buildParameters);
        builder.append(",").append("EthPorts").append("=").append("EthPorts").append(",");
        return builder.toString();
    }

    @Override
    public String toPy() {
        return processParams();
    }
}

