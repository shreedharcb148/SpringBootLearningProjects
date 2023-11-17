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
public class ComBlock2Function extends AbstractFunction {


    public ComBlock2Function() {
       keysSet.add("Interface");keysSet.add("Mode");keysSet.add("Send_Data"); 
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
        String buildParametersComblock = super.buildParameters(processedInputParams);
        String[] split = processedInputParams.get("Interface").split("\\.");
        String Port = split[0]+".ECUPORT";
        String EcuIp= split[0]+".ECUIP";
        String paramsBuilding = buildParametersComblock+","+"ECUIP="+EcuIp+","+"ECUPORT="+Port+",";
        String buildData = paramsBuilding.replace("&","");
        return buildData;
    }
    @Override
    public String toPy() {
        return processParams();
    }
}
