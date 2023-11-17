/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pav2py.excel.pts.functions;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author WLH1KOR
 */
public class StringCompareFunction extends AbstractFunction {

    Set<String> keysSet ;
    public StringCompareFunction() {
       keysSet = new LinkedHashSet<>();
       keysSet.add("String1"); keysSet.add("String2"); keysSet.add("Printout_Protocol"); keysSet.add("Save_to_File"); 
    }
    
    @Override
    public String processParams() {
        if (super.getOutPutParam() != null && !super.getOutPutParam().isEmpty()) {
            return super.getOutPutParam() + " = " + super.getName() + "(" + "globals(), " + processInputParams(super.getParams()) + ")";
        }
        return super.getName() + "(" + "globals(), " + processInputParams(super.getParams()) + ")";
    }

    private String processInputParams(String params) {
        //pre process paramaters remove newline,replace ; with , and remove last semi colon
        params = super.getProcessedParameter(params);

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
