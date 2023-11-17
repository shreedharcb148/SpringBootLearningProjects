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
public class UDSRawIni1Function extends AbstractFunction {
    public UDSRawIni1Function() {
        keysSet.add("Eth_Handle");keysSet.add("IP_Protocol");
        keysSet.add("Port");keysSet.add("Timeout");
        keysSet.add("Application"); keysSet.add("Mode");

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
        String buildParametersUDSRawIni = super.buildParameters(processedInputParams);
        String[] split = processedInputParams.get("Eth_Handle").split("\\.");
        String Port = split[0]+".LOCALPORT";
         String paramsBuilding = buildParametersUDSRawIni+","+"LocalPort="+Port+",";
       //process parameters and build for function prototype
         String buildData = paramsBuilding.replace("&","");
         return buildData;
        
    }

    @Override
    public String toPy() {
        return processParams();
    }
}
