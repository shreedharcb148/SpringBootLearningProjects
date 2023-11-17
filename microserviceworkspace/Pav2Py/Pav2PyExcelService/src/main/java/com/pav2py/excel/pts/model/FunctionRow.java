//Copyright (C) Robert Bosch GmbH 2022. All rights reserved.
package com.pav2py.excel.pts.model;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import com.pav2py.excel.model.abstraction.AbstarctTestRow;
import com.pav2py.excel.pts.functions.AbstractFunction;
import com.pav2py.excel.pts.functions.Control_CSFunction;
import com.pav2py.excel.utlilty.BasicUtilities;
import com.pav2py.excel.utlilty.Constants;
import com.pav2py.excel.utlilty.EDataType;
import com.pav2py.excel.utlilty.Util;

/**
 * Model for a Tes row from TestGateSheet
 *
 * @author PJA7COB
 */
public class FunctionRow extends AbstarctTestRow {

    private String ecuVariants;
    private String testConditions;
    private String externalParams;
    private String measurementObject;
    private AbstractFunction function;
    private List<AbstractFunction> externalSubFunctions;
    private String comment;
    private ChapterRow group;
    private final StringBuilder pyBuilder = new StringBuilder();

    public String getEcuVariants() {
        return ecuVariants;
    }

    public void setEcuVariants(String ecuVariants) {
        this.ecuVariants = ecuVariants;
    }

    public String getTestConditions() {
        return testConditions;
    }

    public void setTestConditions(String testConditions) {
        this.testConditions = testConditions;
    }

    public String getExternalParams() {
        return externalParams;
    }

    public void setExternalParams(String externalParams) {
        this.externalParams = externalParams;
    }

    public String getMeasurementObject() {
        return measurementObject;
    }

    public void setMeasurementObject(String measurementObject) {
        this.measurementObject = measurementObject;
    }

    public AbstractFunction getFunction() {
        return function;
    }

    public void setFunction(AbstractFunction function) {
        this.function = function;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ChapterRow getGroup() {
        return group;
    }

    public void setGroup(ChapterRow group) {
        this.group = group;
    }

    public List<AbstractFunction> getExternalSubFunctions() {
        return externalSubFunctions;
    }

    public void setExternalSubFunctions(List<AbstractFunction> externalSubFunctions) {
        this.externalSubFunctions = externalSubFunctions;
    }

    @Override
    public String toPy() {
        pyBuilder.setLength(0);
        String func;
        Stack stack;
        try {
            if (null != function) {
                func = function.toPy();
                stack = function.getStack();

                if (getFunction().getName().trim().equals(Constants.CONTROL_CS_FUNCTION_NAME)) {
                    Control_CSFunction con = (Control_CSFunction) function;
                    con.indentationUsingStack();
                    pyBuilder.append(Util.getNumberofTabs(stack.size() - 1));
                    pyBuilder.append(func);
                    if (function.getEndif() == true) {
                        stack.pop();
                    }
                } else {
                    if (!stack.isEmpty()) {
                        if ("cs".equals(stack.get(stack.size() - 1))) {
                            stack.push("other");
                        }
                    }

                    //Step : 1 - removed the last close brace and form the builder
                    func = func.substring(0, func.length() - 1);
                    //append indentation depends on stacksize
                    pyBuilder.append(Util.getNumberofTabs(stack.size() - 1));

                    //if func output contains more lines ,apply indentation to all the lines
                    pyBuilder.append(func.replace(Util.addNewLineInWindowsStyle(), Util.addNewLineInWindowsStyle() + Util.getNumberofTabs(stack.size() - 1)));

                    //Step : 2 - Add position and measurement object to argument
                    if (func.endsWith(",")) {
                        pyBuilder.append("Position = ");
                    } else {
                        pyBuilder.append(",Position = ");
                    }
                    pyBuilder.append(BasicUtilities.getPyStringValueSafe(String.valueOf(getPosition())));

                    checkMinMaxDim();

                    checkMinMaxDimForDataExchange();

                    checkMinMaxDimForWaitUntil();

                    checkMinMaxForCalculate();

                    pyBuilder.append(",Measurement_object = ").append("\"").append(BasicUtilities.getPyStringValueSafe(measurementObject)).append("\"");

                    //Step : 2 - append no_log paramter - as of now its false for all
                    pyBuilder.append(",no_log=False");

                    //Step : 3 - Close function
                    pyBuilder.append(")");

                    boolean commentAdded = false;
                    if (comment != null && !comment.isEmpty()) {
						pyBuilder.append(Constants.DOUBLE_TAB).append("#Comment:").append(comment);
                        commentAdded = true;
                    }
                    if (testConditions != null && !testConditions.isEmpty()) {
                        if (!commentAdded) {
                            pyBuilder.append(Constants.DOUBLE_TAB).append("#").append("Test Condition:").append(testConditions);
                        } else {
                            pyBuilder.append(Constants.DOUBLE_TAB).append("Test Condition:").append(testConditions);
                        }
                    }

                    //check for externalSubFunctions empty
                    if (null != externalSubFunctions) {
                        pyBuilder.append(Util.addNewLineInWindowsStyle()).append(Constants.DOUBLE_TAB);

                        //stack empty check is not required, here externalsubfunctions comes together with function
                        pyBuilder.append(stack.size() - 1);
                        pyBuilder.append(addExternalParamsAsSubFunctions(getExternalSubFunctions()));
                    }
                }
            } else {
                //if only external functions found ,add "other" in stack
                stack = getExternalSubFunctions().get(0).getStack();
                if (!stack.isEmpty()) {
                    if ("cs".equals(stack.get(stack.size() - 1))) {
                        stack.push("other");
                    }
                }
                if (null != externalSubFunctions) {
                    //stack empty check is required as main function is null here
                    if (!getExternalSubFunctions().get(0).getStack().isEmpty()) {
                        pyBuilder.append(Util.getNumberofTabs(stack.size() - 1));
                    }
                    pyBuilder.append(addExternalParamsAsSubFunctions(getExternalSubFunctions()));
                }

            }

        } catch (Exception e) {
           // MasterLogger.getInstance().logError("Unable to process the function at position " + super.getPosition() + " : check for valid input parameters");
        }

        return pyBuilder.toString();
    }

    public void checkMinMaxForCalculate() {
        if (getFunction().getName().equals(Constants.CALCULATE_FUNCTION_NAME)) {
            if (getFunction().getDimension() != null) {
                pyBuilder.append(",Dim = ").append("\"").append(BasicUtilities.getPyStringValueSafe(String.valueOf(getFunction().getDimension()))).append("\"");
            } else {
                pyBuilder.append(",Dim = ").append("\"\"");

            }
            if (!getFunction().getMin().isEmpty()) {
                pyBuilder.append(",Min = ").append(checkMinMaxValueForConstantAndVariableCalculation(getFunction().getMin()));
            } else {
                pyBuilder.append(",Min = ").append("\"\"");
            }
            if (!getFunction().getMax().isEmpty()) {
                pyBuilder.append(",Max = ").append(checkMinMaxValueForConstantAndVariableCalculation(getFunction().getMax()));
            } else {
                pyBuilder.append(",Max = ").append("\"\"");
            }
        }
    }

    public void checkMinMaxDimForWaitUntil() {
        if (getFunction().getName().equals(Constants.WAITUNITL1_FUNCTION_NAME)) {
            if (getFunction().getDimension() != null) {
                pyBuilder.append(",Dim = ").append("\"").append(BasicUtilities.getPyStringValueSafe(String.valueOf(getFunction().getDimension()))).append("\"");
            } else {
                pyBuilder.append(",Dim = ").append("\"\"");

            }
            pyBuilder.append(",Min = ").append(getFunction().getMin());
            pyBuilder.append(",Max = ").append(getFunction().getMax());
        }
    }

    public void checkMinMaxDimForDataExchange() {
        Set<String> validFunctionNames = getFunctionNameMesDataExchange();
        if (validFunctionNames.contains(getFunction().getName())) {
            if (getFunction().getDimension() != null) {
                pyBuilder.append(",Dim = ").append("\"").append(BasicUtilities.getPyStringValueSafe(String.valueOf(getFunction().getDimension()))).append("\"");
                pyBuilder.append(",Min = ").append(checkMinMaxValueForConstantAndVariable(String.valueOf(getFunction().getMin()), getFunction().getDimension().toString()));
                pyBuilder.append(",Max = ").append(checkMinMaxValueForConstantAndVariable(String.valueOf(getFunction().getMax()), getFunction().getDimension().toString()));
                pyBuilder.append(",MESFileLocation=").append("Mes_File_Loc");
            } else {
                pyBuilder.append(",Dim = ").append("\"\"");
                pyBuilder.append(",Min = ").append(checkMinMaxValueForConstantAndVariable(BasicUtilities.getPyStringValueSafe(getFunction().getMin()), ""));
                pyBuilder.append(",Max = ").append(checkMinMaxValueForConstantAndVariable(BasicUtilities.getPyStringValueSafe(getFunction().getMax()), ""));
                pyBuilder.append(",MESFileLocation=").append("Mes_File_Loc");
            }
            pyBuilder.append(",mes_data_section = mes_data_section");
        }
    }

    
    public void checkMinMaxDim() {
        Set<String> validFunctionNames = getFunctionNameAsList();
        if (validFunctionNames.contains(getFunction().getName())) {
            if (getFunction().getDimension() != null) {
                pyBuilder.append(",Dim = ").append("\"").append(BasicUtilities.getPyStringValueSafe(String.valueOf(getFunction().getDimension()))).append("\"");
                pyBuilder.append(",Min = ").append(checkMinMaxValueForConstantAndVariable(String.valueOf(getFunction().getMin()), getFunction().getDimension().toString()));
                pyBuilder.append(",Max = ").append(checkMinMaxValueForConstantAndVariable(String.valueOf(getFunction().getMax()), getFunction().getDimension().toString()));

            } else {
                pyBuilder.append(",Dim = ").append("\"\"");
                pyBuilder.append(",Min = ").append(checkMinMaxValueForConstantAndVariable(BasicUtilities.getPyStringValueSafe(getFunction().getMin()), ""));
                pyBuilder.append(",Max = ").append(checkMinMaxValueForConstantAndVariable(BasicUtilities.getPyStringValueSafe(getFunction().getMax()), ""));

            }
        }
    }

    private StringBuilder addExternalParamsAsSubFunctions(List<AbstractFunction> externalSubFunctions) {

        StringBuilder externalParams = new StringBuilder();
        for (int i = 0; i < externalSubFunctions.size(); i++) {
            if (!externalSubFunctions.get(i).getName().equalsIgnoreCase("Tw") && !externalSubFunctions.get(i).getName().equalsIgnoreCase("ConfigPowerSupply")
                    && !externalSubFunctions.get(i).getName().equalsIgnoreCase(Constants.POWER_ON)
                    && !externalSubFunctions.get(i).getName().equalsIgnoreCase("ps_off = " + Constants.POWER_Off)) {
                externalParams.append("#");
            }
//            if (!externalSubFunctions.get(i).getName().equalsIgnoreCase("Tw")) {
//                externalParams.append("#");
//            }

            //invoking toPy() from each ExternalParamFunction  Object
            externalParams.append(externalSubFunctions.get(i).toPy());

            //logic to add newline only if next external function is present
            if (i <= externalSubFunctions.size() - 2) {
                if (externalSubFunctions.get(i + 1) != null) {
                    externalParams.append(Util.addNewLineInWindowsStyle()).append(Constants.DOUBLE_TAB);
                }
            }

        }
        return externalParams;
    }

    private String checkMinMaxValueForConstantAndVariable(String valueOf, String dim) {

        String output = "";

        if (dim.toLowerCase().equals("int")) {
            output = valueOf;
        } else {
            if (Util.validate(valueOf) == EDataType.INVALID) {
                output = "\"" + valueOf + "\"";
            } else {
                output = valueOf;
            }
        }
        return output.replace("&", "");
    }

    private String checkMinMaxValueForConstantAndVariableCalculation(String valueOf) {
        String output = "";
        if (Util.validate(valueOf) != EDataType.INVALID || Util.checkForNumericFloatingValue(valueOf)) {
            output = valueOf;
        } else {
            output = "\"" + valueOf + "\"";

        }
        return output.replace("&", "");
    }
    
    
    public Set<String> getFunctionNameAsList() {
        Set<String> funcList = new LinkedHashSet<>();
        funcList.add("StringCompare");
        funcList.add("StringCopy");
        funcList.add("StrCmp_CS");
        funcList.add("StrCopy_CS");
        funcList.add("StrCat_CS");
        funcList.add("CheckResponse");
        funcList.add("BlockInt");
        funcList.add("BlockHexStr");
        funcList.add("BlockHex");
        funcList.add("BlockStr");
        funcList.add("IntToStr");
        funcList.add("HexSubStrToHexStr");
        funcList.add("HexSubStrToInt");
        funcList.add("Anzahl");
        funcList.add("HexSubStrToFloat32");
        funcList.add("BlockFloat32");
        funcList.add("FloatToStr");
        funcList.add("StrToHexString_CS");
        funcList.add("Break");
        funcList.add("ComBlock3");
        funcList.add("ComBlock2");
        funcList.add("CAN2000Ini4");
        funcList.add("CANFD2000Ini1");
        funcList.add("CANFD2000Ini");
        funcList.add("ETH_Ini");
        funcList.add("IPv4_Ini");
        funcList.add("UDSRawIni1");
        funcList.add("UDSRawDown");
        funcList.add("Eth_Down1");
        funcList.add("ComStop1");
        funcList.add("BreakStr_CS");
        return funcList;

    }

    public Set<String> getFunctionNameMesDataExchange() {

        Set<String> funcMesList = new LinkedHashSet<>();
        funcMesList.add("Data_AddData_Num");
        funcMesList.add("Data_AddData_Str");
        funcMesList.add("Data_IdPlate");
        funcMesList.add("DataWrite");
        return funcMesList;

    }


}
