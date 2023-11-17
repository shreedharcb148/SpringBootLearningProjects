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
public class CANFD2000Ini1Function extends AbstractFunction {
    

    public CANFD2000Ini1Function() {
        keysSet.add("Interface");
        keysSet.add("CAN_Baudrate");
        keysSet.add("Frame_Length");
        keysSet.add("Termination_Resistor");
        keysSet.add("Message_Architecture");
        keysSet.add("ID_Send");
        keysSet.add("ID_Send_Length");
        keysSet.add("ID_Read");
        keysSet.add("ID_Read_Length");
        keysSet.add("CANFD_Enable");
        keysSet.add("CANFD_Standard");
        keysSet.add("CANFD_Baud_Switch");
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

        //create input parameter as key value pair separated by coma
        //process parameters and build for function prototype
        return super.buildParametersWithHex(processedInputParams);
    }

    @Override
    public String toPy() {
        return processParams();
    }
}

