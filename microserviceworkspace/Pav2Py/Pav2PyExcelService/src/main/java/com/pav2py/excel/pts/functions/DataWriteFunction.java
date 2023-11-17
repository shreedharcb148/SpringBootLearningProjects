/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pav2py.excel.pts.functions;

import java.util.Map;

/**
 *
 * @author IXL1KOR
 */


public class DataWriteFunction extends AbstractFunction {

    public DataWriteFunction() {
       keysSet.add("Data"); keysSet.add("Kind_of_Data"); keysSet.add("Data_ID"); keysSet.add("Mode");keysSet.add("Timeout");
    
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

        //process parameters and build for function prototype
        return super.buildParameters(processedInputParams);
    }

    @Override
    public String toPy() {
        return processParams();
    }

}
