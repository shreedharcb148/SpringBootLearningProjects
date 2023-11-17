/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pav2py.excel.pts.functions;

import java.util.Map;

import com.pav2py.excel.utlilty.Util;

/**
 *
 * @author WLH1KOR
 */
public class Control_CSFunction extends AbstractFunction {

    Map<String, String> processedInputParams;
    public Control_CSFunction() {
        keysSet.add("Type");
        keysSet.add("Condition");
    }

    @Override
    public String processParams() {
        //pre process paramaters remove newline,replace ; with , and remove last semi colon
        super.processParams();
        return processInputParams();
    }

    private String processInputParams() {
        StringBuilder builder = new StringBuilder();
        String output = "";
        String params = getParams();

        //get all inputParameters of the function in map
        //this method will process inputParameters string differently, no splitting of string by any chars like "," and "="
        processedInputParams = super.processInputParameters(keysSet, params);
        
        /*
            considering Type and Condition always present in Control_CS function
        */
        switch (processedInputParams.get("Type").toUpperCase()) {
            case "WHILE":
            case "IF":
            case "ELSEIF":
                //replace elseif by elif in processedInputParams map
                if (processedInputParams.get("Type").equalsIgnoreCase("ELSEIF")) {
                    processedInputParams.put("Type", "ELIF");
                }

                //check if condition is there oor nor ,for else and endif condition is none
                if (processedInputParams.containsKey("Condition")) {
                    output = buildIfCondtion(processedInputParams.get("Type").toLowerCase(), processedInputParams.get("Condition"));
                }
                break;
                
            case "ELSE":
                output = "else:";
                break;
            
            case "BREAK":
                if (processedInputParams.containsKey("Condition")) {
                    //hardcode for break
                    //construct block with if condition with indentation
                    builder.append(buildIfCondtion("if", processedInputParams.get("Condition")));
                    //after if add break to new line
                    builder.append(Util.addNewLineInWindowsStyle());

                    //reading stack size to indent the break after if block
                    //taking +1 to make indent in if block
                    builder.append(Util.getNumberofTabs(stack.size() + 2));
                    builder.append("break");

                    output = builder.toString();
                } else {
                    output = "break";
                }
                break;
                
            default:
                if (processedInputParams.get("Type").equalsIgnoreCase("ENDIF")) {
                    output = "#endif";
                } else if (processedInputParams.get("Type").equalsIgnoreCase("ENDWHILE")) {
                    output = "#endwhile";
                } else {
                    output = "#" + processedInputParams.get("Type");
                }
                break;
        }
//        //handling endif ,not required in TG sheet
//        if (processedInputParams.get("Type").equalsIgnoreCase("WHILE") || processedInputParams.get("Type").equalsIgnoreCase("IF") || processedInputParams.get("Type").equalsIgnoreCase("ELSEIF")
//                || processedInputParams.get("Type").equalsIgnoreCase("ELSE")) {
//            
//            //replace elseif by elif in processedInputParams map
//            if(processedInputParams.get("Type").equalsIgnoreCase("ELSEIF")){
//                processedInputParams.put("Type","ELIF");
//            }
//
//            //check if condition is there oor nor ,for else and endif condition is none
//            if (processedInputParams.containsKey("Condition")) {
//                output = buildIfCondtion(processedInputParams.get("Type").toLowerCase(), processedInputParams.get("Condition"));
//                
//            }
//        }else if(processedInputParams.get("Type").equalsIgnoreCase("BREAK") ){
//             if (processedInputParams.containsKey("Condition")) {
//                //hardcode for break
//                //construct block with if condition with indentation
//                builder.append(buildIfCondtion("if", processedInputParams.get("Condition")));
//                //after if add break to new line
//                builder.append(Util.addNewLineInWindowsStyle());
//                
//                //reading stack size to indent the break after if block
//                //taking +1 to make indent in if block
//                builder.append(Util.getNumberofTabs(stack.size()+1));
//                builder.append("break");
//                
//                output = builder.toString();
//             }else{
//                  output = "break";
//             }
//        }else{
//            if(processedInputParams.get("Type").equalsIgnoreCase("ENDIF")){
//                output = "#endif";
//            }else if(processedInputParams.get("Type").equalsIgnoreCase("ENDWHILE")){
//                output = "#endwhile";
//            }else{
//                output = "#"+processedInputParams.get("Type");
//            }
//            
//        }

        return output;
    }
    public void indentationUsingStack() {
        setEndif(false);
        //if type is endif
        //check top value ,if other remove it from stack and adjust top
        if (processedInputParams.get("Type").contains("ENDIF") || processedInputParams.get("Type").contains("ENDWHILE")) {
            setEndif(true);
            if (!stack.isEmpty()) {
                if ("other".equals(stack.get(stack.size() - 1))) {
                    stack.pop();
                }
            }
          
        } 
        //if type is else/elseif
        //check top value ,if other remove it from stack and adjust top 
        else if (processedInputParams.get("Type").contains("ELSE") ||processedInputParams.get("Type").contains("ELIF")) {
            if ("other".equals(stack.get(stack.size() - 1))) {
                stack.pop();
            }
        } 
        //if type is if
        //push if word to stack and adjust pop
        else if (processedInputParams.get("Type").trim().contains("IF") || processedInputParams.get("Type").trim().contains("WHILE")) {
            if (stack.isEmpty()) {
                stack.push("cs");
            } else {
                if ("other".equals(stack.get(stack.size() - 1))) {
                    stack.pop();
                    stack.push("cs");
                } else {
                    stack.push("cs");
                }
            }

        } 
    }
    
    @Override
    public String toPy() {
        return processParams();
    }

}
