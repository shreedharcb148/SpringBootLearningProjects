/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pav2py.excel.pts.functions;

import java.util.HashSet;
import java.util.Set;

import com.pav2py.excel.utlilty.Constants;
import com.pav2py.excel.utlilty.Util;

/**
 *
 * @author IXL1KOR
 */
public class CalculateFunction extends AbstractFunction {

    public CalculateFunction() {
    }

    @Override
    public String toPy() {
        StringBuilder builder = new StringBuilder();
        builder.append("#").append(" ").append(super.getName()).append(" ").append("FUNCTION");
        builder.append(Util.addNewLineInWindowsStyle()).append(Constants.DOUBLE_TAB).append("try:");
        builder.append(Util.addNewLineInWindowsStyle()).append(Constants.TRIPLE_TAB).append(this.commentRoundParameter());
        builder.append(Util.addNewLineInWindowsStyle()).append(Constants.DOUBLE_TAB).append("except Exception as e:").append(Util.addNewLineInWindowsStyle());
        builder.append(Constants.TRIPLE_TAB).append("traceback.print_exc()");
        builder.append(Constants.TRIPLE_TAB).append(Util.addNewLineInWindowsStyle()).append(Constants.TRIPLE_TAB);
        

        String FormulaKey  = (mathFunctionHandling().contains("Formula")) ? "Formula" : (mathFunctionHandling().contains("Result") ? "Result" : "invalidKey");
        String isOutputPresent;
        if(commentRoundParameter().contains("Round")){
            isOutputPresent = (!super.getOutPutParam().isEmpty())? (super.getOutPutParam()+ "=check_result(globals(),RoundingValue=Round,Result="+FormulaKey + ")") : "check_result(globals(),RoundingValue=Round,Result="+FormulaKey + ")";
        }
        else{
            isOutputPresent = (!super.getOutPutParam().isEmpty())? (super.getOutPutParam() + "=check_result(globals(),Result="+FormulaKey + ")") : "check_result(globals(),Result="+FormulaKey + ")";
        }
        
        if (!"invalidKey".equals(FormulaKey)) {
            builder.append(FormulaKey).append("=").append("e");
            builder.append(Util.addNewLineInWindowsStyle());
            builder.append(Constants.DOUBLE_TAB).append(isOutputPresent);
          
        }else{
         
           builder.append(Constants.DOUBLE_TAB).append("Result = None");
        }
       
        return builder.toString();

    }

    //This method, convert the Math Functions from upper case to lower case ex:SIN(X) To sin(X)
    public String mathFunctionHandling() {
        Set<String> constantsMath = ConstantsMath();
        String outputStr = "";
        for (String constants : constantsMath) {
            if (super.getParams().contains(constants)) {
                outputStr = super.getParams().replace(constants.substring(0, constants.length() - 1), constants.substring(0, constants.length() - 1).toLowerCase());
                return outputStr.trim();
            }
        }
        return super.getParams();
    }

    //To Comment the Round Parameter.
    public String commentRoundParameter() {
        StringBuilder result = new StringBuilder();
        String[] input = mathFunctionHandling().split("\n");
        for (String split : input) {
           
            split = split.trim();
                result.append(split).append(";");
        }
        //To Remove & and last ;
        String out = result.toString().replace(";", ";"+Util.addNewLineInWindowsStyle()+Constants.TRIPLE_TAB).replace("&", "");
        
        out = out.substring(0, out.length() - 1);
        if(out.contains("G_NERR")){
            out=out.replace("G_NERR", "Functions.G_NERR");
        }
        return out;
    }

    public static Set<String> ConstantsMath() {
        Set<String> set = new HashSet();
        set.add("SIN(");
        set.add("COS(");
        set.add("ASIN(");
        set.add("ACOS(");
        set.add("TAN(");
        set.add("ATAN(");
        set.add("ATAN2(");
        set.add("EXP(");
        set.add("INT(");
        set.add("LOG10(");
        set.add("LOG2(");
        set.add("SQRT(");
        set.add("ABS(");
        return set;

    }
    
    
    public  String outputParam(){
        String output=super.getOutPutParam();
        if(output.contains("G_NERR")){
            output=output.replace("G_NERR", "Functions.G_NERR");
        }
        return output;
    }
}