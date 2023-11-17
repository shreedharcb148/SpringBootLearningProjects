/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pav2py.excel.pts.functions;

/**
 *
 * @author WLH1KOR
 */
public class ExternalParamFunction extends AbstractFunction {

    public ExternalParamFunction() {
    }

    @Override
    public String toPy() {
        return super.getName() + "(" + super.getParams() + ")";
    }
    
}
