/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pav2py.excel.pts.functions;

/**
 *
 * @author WLH1KOR
 */
public class NotImplementedFunction extends AbstractFunction {
       public NotImplementedFunction() {
      
    }
    @Override
    public String processParams() {
        super.processParams();
        if (super.getOutPutParam() != null && !super.getOutPutParam().isEmpty()) {
            return super.getOutPutParam() + " = " + super.getName() + "(" + "globals(), " + processInputParams() + ")";
        }
        return super.getName() + "(" + "globals(), " + processInputParams() + ")";
    }
    
    public String processInputParams() {
        return "";
    }
    
    @Override
    public String toPy() {
        return "#" + processParams();
    }
}
