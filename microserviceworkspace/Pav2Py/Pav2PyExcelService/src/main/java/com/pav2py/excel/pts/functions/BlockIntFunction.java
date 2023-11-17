/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pav2py.excel.pts.functions;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author WLH1KOR
 */
public class BlockIntFunction extends AbstractFunction {

    public BlockIntFunction() {
        //keysSet.add("Format");
        keysSet.add("LowByte");
        keysSet.add("HighByte");
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
        Map<String, String> processedInputParams = new LinkedHashMap<>();
        String params = getParams();

        //read RDCL value present at the beginning and trim it
        if (params.startsWith("RDCL")) {
            String RDCL = params.split(";")[0];
            processedInputParams.put("RDCL", RDCL);
            //trim the RDCL from entire string
            params = params.substring(RDCL.length() + 1, params.length());
        }

        //get all inputParameters of the function in map
        //this method will process inputParameters string differently, no splitting of string by any chars like "," and "="
        processedInputParams.putAll(super.processInputParameters(keysSet, params));

         //process parameters and build for function prototype
        return super.buildParameters(processedInputParams);
    }

    @Override
    public String toPy() {
        return processParams();
    }
}
