package com.pav2py.excel.pts.functions;

import java.util.Map;

/**
 *
 * @author IXL1KOR
 */
public class BreakFunction extends AbstractFunction {

    public BreakFunction() {
        keysSet.add("Input_Value");
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