//Copyright (C) Robert Bosch GmbH 2022. All rights reserved.
package com.pav2py.excel.pts.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.apache.poi.ss.formula.functions.NotImplementedFunction;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;

import com.pav2py.excel.model.abstraction.AbstractSheet;
import com.pav2py.excel.model.abstraction.IRow;
import com.pav2py.excel.pts.functions.AbstractFunction;
import com.pav2py.excel.pts.functions.AnzahlFunction;
import com.pav2py.excel.pts.functions.BlockFloat32Function;
import com.pav2py.excel.pts.functions.BlockHexFunction;
import com.pav2py.excel.pts.functions.BlockHexStrFunction;
import com.pav2py.excel.pts.functions.BlockIntFunction;
import com.pav2py.excel.pts.functions.BlockStrFunction;
import com.pav2py.excel.pts.functions.BreakFunction;
import com.pav2py.excel.pts.functions.CAN2000Ini4Function;
import com.pav2py.excel.pts.functions.CANFD2000Ini1Function;
import com.pav2py.excel.pts.functions.CANFD2000IniFunction;
import com.pav2py.excel.pts.functions.CalculateFunction;
import com.pav2py.excel.pts.functions.CheckResponseFunction;
import com.pav2py.excel.pts.functions.ComBlock2Function;
import com.pav2py.excel.pts.functions.ComBlock3Function;
import com.pav2py.excel.pts.functions.ComStop1Function;
import com.pav2py.excel.pts.functions.Control_CSFunction;
import com.pav2py.excel.pts.functions.DataAddDataNumFunction;
import com.pav2py.excel.pts.functions.DataAddDataStrFunction;
import com.pav2py.excel.pts.functions.DataIdPlateFunction;
import com.pav2py.excel.pts.functions.DataWriteFunction;
import com.pav2py.excel.pts.functions.ETHIni;
import com.pav2py.excel.pts.functions.Eth_Down1Function;
import com.pav2py.excel.pts.functions.ExternalParamFunction;
import com.pav2py.excel.pts.functions.FloatToStrFunction;
import com.pav2py.excel.pts.functions.HexSubStrToFloat32;
import com.pav2py.excel.pts.functions.HexSubStrToHexStrFunction;
import com.pav2py.excel.pts.functions.HexSubStrToIntFunction;
import com.pav2py.excel.pts.functions.IPv4_IniFunction;
import com.pav2py.excel.pts.functions.IntToStrFunction;
import com.pav2py.excel.pts.functions.PrintOutFunction;
import com.pav2py.excel.pts.functions.SetVariableFunction;
import com.pav2py.excel.pts.functions.Set_ResponseFunction;
import com.pav2py.excel.pts.functions.StrCatFunction;
import com.pav2py.excel.pts.functions.StrToHexString_CSFunction;
import com.pav2py.excel.pts.functions.StringCompareFunction;
import com.pav2py.excel.pts.functions.StringCopyFunction;
import com.pav2py.excel.pts.functions.UDSRawDownFunction;
import com.pav2py.excel.pts.functions.UDSRawIni1Function;
import com.pav2py.excel.pts.functions.WaitUntil1Function;
import com.pav2py.excel.utlilty.Constants;
import com.pav2py.excel.utlilty.ETestCondition;
import com.pav2py.excel.utlilty.Util;

/**
 * Holds all the row data from a TestGate sheet( any of TG_EOL, TG_Flash or
 * TG_Panel)
 *
 *
 * @author PJA7COB
 */
public class TestGateSheet extends AbstractSheet {

    private MissingCellPolicy isRowNull = Row.MissingCellPolicy.RETURN_BLANK_AS_NULL;
    private DataFormatter formatter = new DataFormatter();
    private int ecuVariant;
    private Set<ETestCondition> testCondns;

    private DeviceConfigurationController deviceConfigurationController;

    public DeviceConfigurationController getDeviceConfigurationController() {
        return deviceConfigurationController;
    }

    public void setDeviceConfigurationController(DeviceConfigurationController deviceConfigurationController) {
        this.deviceConfigurationController = deviceConfigurationController;
    }
    Stack<String> stack = new Stack<String>();

    int cc;

    public TestGateSheet(String name, Sheet sheet, int ecuVariant, Set<ETestCondition> testConditions) {
        super(name, sheet);
        this.ecuVariant = ecuVariant;
        this.testCondns = testConditions;
        cc = 0;

    }

    @Override
    public boolean validate() {
        Row row = super.getSheet().getRow(0);
        try {
            if (row.getCell(0).toString().equals(Constants.PTS_TG_SHEET_ROWTYPE_HEADER) && row.getCell(1).toString().equals(Constants.PTS_TG_SHEET_POSITION_HEADER)
                    && row.getCell(2).toString().equals(Constants.PTS_TG_SHEET_DUT_VARIANT_HEADER) && row.getCell(3).toString().equals(Constants.PTS_TG_SHEET_TEST_TYPE_HEADER)
                    && row.getCell(4).toString().equals(Constants.PTS_TG_SHEET_EXTERNAL_PARAMETERS_HEADER)
                    && row.getCell(5).toString().equals(Constants.PTS_TG_SHEET_MEASUREMENT_OBJECT_HEADER)
                    && row.getCell(6).toString().equals(Constants.PTS_TG_SHEET_FUNCTION_HEADER) && row.getCell(7).toString().equals(Constants.PTS_TG_SHEET_INPUT_PARAMETERS_HEADER)
                    && row.getCell(8).toString().equals(Constants.PTS_TG_SHEET_OUTPUT_PARAMETERS_HEADER) && row.getCell(9).toString().equals(Constants.PTS_TG_SHEET_MIN_VALUE_HEADER)
                    && row.getCell(10).toString().equals(Constants.PTS_TG_SHEET_MAX_VALUE_HEADER) && row.getCell(11).toString().equals(Constants.PTS_TG_SHEET_DIMENSION_HEADER)
                    && row.getCell(12).toString().equals(Constants.PTS_TG_SHEET_DESCRIPTION_HEADER)) {

                super.setValid(true);
            }
        } catch (Exception e) {
            super.setValid(false);
        }

        return super.isValid();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createRows() {
        try {
            for (int rowindex = 1; rowindex < super.getSheet().getPhysicalNumberOfRows(); rowindex++) {
                boolean isValidRow = true;
                Row row = super.getSheet().getRow(rowindex);

                if (row != null) {
                    //CONDITION FOR SKIPPING HIDDEN ROW
                    if (!row.getZeroHeight()) {
                        for (int coloumnIndex = 0; coloumnIndex < super.getSheet().getRow(rowindex).getPhysicalNumberOfCells(); coloumnIndex++) {
                            XSSFCellStyle cellStyle;
                            //checking if any column having redfont text and strikesout 
                            try {
                                if (row.getCell(coloumnIndex, isRowNull) != null) {
                                    cellStyle = (XSSFCellStyle) row.getCell(coloumnIndex).getCellStyle();

                                    XSSFFont cellFont = cellStyle.getFont();
                                    //CONDITION FOR ANY STRIKEOUT CELL PRESENT IN ROW
                                    if (cellFont.getStrikeout()) {
                                        MasterLogger.getInstance().logError("Skipping " + (rowindex + 1) + "th row from the sheet " + Constants.PTSTG_EOL + " : Strikeout value at cell " + (coloumnIndex + 1));
                                        isValidRow = false;
                                        break;
                                    }
                                }
                            } catch (Exception e) {
                                isValidRow = false;
                                MasterLogger.getInstance().logError("Skipping " + (rowindex + 1) + "th row from the sheet " + Constants.PTSTG_EOL + " : Either cell strikeout or exception occured at cell  " + (coloumnIndex + 1) + " " + e.getMessage());
                                //e.printStackTrace();
                            }
                        }
                        try {
                            if (isValidRow) {
                                if (row.getCell(0, isRowNull) != null) {
                                    if (row.getCell(0).toString().equals(Constants.PTSTG_EOLRowChapType)) {
                                        createChapterRow(row);
                                    } else if (row.getCell(0).toString().equals(Constants.PTSTG_EOLRowCLType)) {
                                        createCommentRow(row);
                                    } else {
                                        MasterLogger.getInstance().logError("Skipping " + (rowindex + 1) + "th row from the sheet " + Constants.PTSTG_EOL + " : Invalid row type ");
                                    }
                                } else if (row.getCell(0, isRowNull) == null) {
                                    createFunctionRow(row, rowindex);
                                }
                            }
                        } catch (Exception e) {
                            MasterLogger.getInstance().logError("Skipping " + (rowindex + 1) + "th row from the sheet " + Constants.PTSTG_EOL + " : Custom Error ");
                        }

                    } else {
                        MasterLogger.getInstance().logError("Skipping " + (rowindex + 1) + "th row from the sheet " + Constants.PTSTG_EOL + " : Hidden row");
                    }
                }
            }
        } catch (Exception e) {
            MasterLogger.getInstance().logError("Unable to process TG_EOL sheet from PTS " + e.getMessage());
        }
    }

    private void createChapterRow(Row row) {
        ChapterRow chapterRow = new ChapterRow();
        if (row.getCell(1, isRowNull) != null) {
            //CHECKING FOR CELL VALUE IS IN FORMULA FORMAT OR NOT
            chapterRow.setPosition(getPostionValue(row.getCell(1)));
        }
        if (row.getCell(5, isRowNull) != null) {
            chapterRow.setName(row.getCell(5).toString());
        }
        super.getRows().add(chapterRow);
    }

    private void createCommentRow(Row row) {
        CommentRow commentRow = new CommentRow();
        if (row.getCell(1, isRowNull) != null) {
            //CHECKING FOR CELL VALUE IS IN FORMULA FORMAT OR NOT
            commentRow.setPosition(getPostionValue(row.getCell(1)));
        }
        if (row.getCell(5, isRowNull) != null) {
            commentRow.setComment(row.getCell(5).toString());
        }
        super.getRows().add(commentRow);
    }

    private void createFunctionRow(Row row, int rowindex) {

        //CHECKING IF FUNCTION NAME IS NOT EMPTY/NULL
        if (row.getCell(6, isRowNull) != null && !row.getCell(6).toString().isEmpty()) {
            FunctionRow functionRow = new FunctionRow();

            //CHECKING WHETHER USER SELECTED VARIANT IS MATCHING WITH ECU VARIANT COLUMN
            if (formatter.formatCellValue(row.getCell(2)).contains(this.ecuVariant + "") || row.getCell(2, isRowNull) == null) {
                functionRow.setEcuVariants(this.ecuVariant + "");

                //CHECKING FOR TEST CONDITION
                if (checkTestCondition(row, functionRow)) {
                    //ADDING FUNCTION OBJECT IN FUNCTIONFROW
                    functionRow.setFunction(createFunctionObject(row));
                    if (row.getCell(1, isRowNull) != null) {
                        //CHECKING FOR CELL VALUE IS IN FORMULA FORMAT OR NOT
                        functionRow.setPosition(getPostionValue(row.getCell(1)));

                    } else {
                        functionRow.setPosition(0);
                    }

                    if (row.getCell(4, isRowNull) != null) {
                        //functionRow.setExternalParams(row.getCell(4).toString().replace("\n", " "));
                        //if external params present ,process and add each parameter as sub functions and add them in externalSubFunctions list of functionRow object
                        functionRow.setExternalSubFunctions(processExternalParamsAsSubFunctions(row.getCell(4).toString()));
                    } else {
                        functionRow.setExternalSubFunctions(null);
                        //functionRow.setExternalParams(null);
                    }

                    if (row.getCell(5, isRowNull) != null) {
                        functionRow.setMeasurementObject(row.getCell(5).toString().replace("\n", " "));
                    } else {
                        functionRow.setMeasurementObject(null);
                    }

                    if (row.getCell(12, isRowNull) != null) {
                        functionRow.setComment(row.getCell(12).toString().replace("\n", " "));
                    } else {
                        functionRow.setComment(null);
                    }
                    super.getRows().add(functionRow);
                } else {
                    MasterLogger.getInstance().logError("Skipping " + (rowindex + 1) + "th row from the sheet " + Constants.PTSTG_EOL + " : Test condition failed");
                }
            } else {
                MasterLogger.getInstance().logError("Skipping " + (rowindex + 1) + "th row from the sheet " + Constants.PTSTG_EOL + " : Mismatch of selected ECU variant");
            }

        } else if (row.getCell(4, isRowNull) != null && !row.getCell(4).toString().isEmpty()) { // if only external params present

            boolean isRowValid = false;

            //check for valid variant row
            if ((formatter.formatCellValue(row.getCell(2)).contains(this.ecuVariant + "") || row.getCell(2, isRowNull) == null) 
                    && checkTestCondition(row, null))   {
                
                isRowValid = true;
            }
            if (isRowValid) {
                FunctionRow functionRow = new FunctionRow();
                functionRow.setExternalSubFunctions(processExternalParamsAsSubFunctions(row.getCell(4).toString()));
                if (row.getCell(1, isRowNull) != null) {
                    //CHECKING FOR CELL VALUE IS IN FORMULA FORMAT OR NOT
                    functionRow.setPosition(getPostionValue(row.getCell(1)));
                } else {
                    functionRow.setPosition(0);
                }
                super.getRows().add(functionRow);
            } else {
                MasterLogger.getInstance().logError("Skipping " + (rowindex + 1) + "th row from the sheet " + Constants.PTSTG_EOL + " : Mismatch of selected ECU variant");
            }

        } else {
            // MasterLogger.getInstance().logError("Skipping " + rowindex + "th row from the sheet " + Constants.PTSTG_EOL + " Invalid row : function name not present");
        }

    }

    private boolean checkTestCondition(Row row, FunctionRow functionRow) {
        boolean rowValid = false;
        if (row.getCell(3, isRowNull) != null) {

            if (!row.getCell(3).toString().isEmpty()) {
                //testCondns set have max two value either L,P,W,Q,V or P
                for (ETestCondition cond : testCondns) {
                    String st = String.valueOf(cond.toString().charAt(0)).toUpperCase();
                    if (row.getCell(3).toString().contains(st)) {
                        rowValid = true;
                    }
                }
            } else {
                rowValid = true;
            }
//            Set<ETestCondition> testCondn = this.testCondns;
//            rowValid = testCondition(row.getCell(3).toString(), testCondn);
//            if (rowValid) {
//                functionRow.setTestConditions(row.getCell(3).toString());
//            }
        } else {
            rowValid = true;
        }
        return rowValid;
    }

    private AbstractFunction createFunctionObject(Row row) {
        AbstractFunction function = null;
        if (row.getCell(6, isRowNull) != null) {
            function = getFunctionObject(row.getCell(6).toString());

            if (null != function) {
                function.setName(row.getCell(6).toString());

                if (row.getCell(7, isRowNull) != null) {
                    function.setParams(row.getCell(7).toString());
                }
                if (row.getCell(8, isRowNull) != null) {
                    function.setOutPutParam(row.getCell(8).toString().replace("&", ""));
                }
                if (row.getCell(9, isRowNull) != null) {
                    function.setMin(formatter.formatCellValue(row.getCell(9)));
                }
                if (row.getCell(10, isRowNull) != null) {
                    function.setMax(formatter.formatCellValue(row.getCell(10)));
                }
                if (row.getCell(11, isRowNull) != null) {
                    function.setDimension(Util.getDataType(row.getCell(11).toString()));
                }
            }

        } else {
            function.setName(null);
        }
        return function;
    }

    @Override
    public List<String> toPy(List<String> pyLines) {
        addHeader(pyLines);

        if (getRows() != null) {
            for (IRow row : getRows()) {
                String func = row.toPy();
                if (func.contains(Constants.POWER_ON) && !func.contains(this.getDeviceConfigurationController().getOnStatus())) {
                    pyLines.add(Constants.DOUBLE_TAB+"#" + func);
                } else if (func.contains(Constants.POWER_Off) && !func.contains(this.getDeviceConfigurationController().getOffStatus())) {
                    pyLines.add(Constants.DOUBLE_TAB+"#" + func);
                } else {
                    pyLines.add(Constants.DOUBLE_TAB + func);
                }
            }
            addFooter(pyLines);
        }
        return pyLines;
    }

    
    private void addHeader(List<String> pyLines) {
        //surround all py code in try finally block
        pyLines.add("def main():");
        pyLines.add(Constants.SINGLE_TAB+"try:");
        pyLines.add(Constants.DOUBLE_TAB+"init_writers()"); //Py code to initialize the report writers
        pyLines.add(Constants.DOUBLE_TAB+"ps_off = False");
        pyLines.add(Constants.DOUBLE_TAB+"#set_digital_outputports(Interface=CAN3,value=2,ports_info=digital_outputports_info,no_log=None)");

    }
    
     private void addFooter(List<String> pyLines) {
        //Pycode to handle csv file creation
        pyLines.add(Constants.SINGLE_TAB+"finally:");
        pyLines.add(Constants.DOUBLE_TAB+"if(not ps_off):");
        pyLines.add(Constants.TRIPLE_TAB+"ps_off = SwitchOffPowerSupply(command=\"" + this.deviceConfigurationController.getOffStatus() + "\")");
        pyLines.add(Constants.DOUBLE_TAB+"print('The Total Count of G_NERR  = ',Functions.G_NERR)");
        pyLines.add(Constants.DOUBLE_TAB+"timestr = datetime.now().strftime(\"%Y%m%d-%H%M%S-%f\")");
        String reportFileName = "Report_" + getName() + "_" + this.testCondns.toString() + "_" + ecuVariant;
        pyLines.add(Constants.DOUBLE_TAB+"file_path = os.path.join(str(os.path.dirname(os.path.abspath(__file__))),\"" + Constants.CSV_REPORTS_FOLDER_NAME + Constants.FILE_SEPARATOR + reportFileName + "\")+"+"timestr");
        pyLines.add(Constants.DOUBLE_TAB+"cust_var_dict['G_NERR'] = Functions.G_NERR");
        pyLines.add(Constants.DOUBLE_TAB+"cust_var_dict['file_path'] = file_path");
        pyLines.add(Constants.DOUBLE_TAB+"create_csv_file(file_path)");
        //pyLines.add("\t\tkill_process_by_port(55216)");
        pyLines.add("if __name__ == \"__main__\":");
        pyLines.add(Constants.SINGLE_TAB+"main()");
    }

    private int getPostionValue(Cell cell) {
        int position = 0;
        //TRY_CATCH : TO HANDLE SCENARIO WHERE FORMULA TYPE IS NOT BOOLEAN,NUMERIC,STRING
        try {
            if (cell.getCellType() == CellType.FORMULA) {
                switch (cell.getCachedFormulaResultType()) {
                    case BOOLEAN:
                        position = 0;
                        //position = (int)cell.getNumericCellValue();
                        break;
                    case NUMERIC:
                        position = (int) cell.getNumericCellValue();
                        break;
                    case STRING:
                        position = (int) cell.getNumericCellValue();
                        break;
                    default:
                        position = 0;
                        break;
                }
            } else {
                position = Integer.parseInt(formatter.formatCellValue(cell));
            }
        } catch (NumberFormatException e) {
            position = 0;
            MasterLogger.getInstance().logError("Exception " + Constants.PTSTG_EOL + " Invalid position value type");

        }

        return position;
    }

    private AbstractFunction getFunctionObject(String toString) {

        AbstractFunction functionObject;
        toString = toString.trim();
        switch (toString) {
            case "PrintOut":
                functionObject = new PrintOutFunction();
                break;
            case "Set_Variable":
                functionObject = new SetVariableFunction();
                break;
            case "StrCat_CS":
                functionObject = new StrCatFunction();
                break;
            case "CAN2000Ini4":
                functionObject = new CAN2000Ini4Function();
                break;
            case "CANFD2000Ini1":
                functionObject = new CANFD2000Ini1Function();
                break;
            case "CANFD2000Ini":
                functionObject = new CANFD2000IniFunction();
                break;
            case "ComBlock2":
                functionObject = new ComBlock2Function();
                break;
            case "ComBlock3":
                functionObject = new ComBlock3Function();
                break;
            case "WaitUntil1":
                functionObject = new WaitUntil1Function();
                break;
            case "StringCompare":
            case "StrCmp_CS":
                functionObject = new StringCompareFunction();
                break;
            case "StringCopy":
            case "StrCopy_CS":
                functionObject = new StringCopyFunction();
                break;
            case "Calculate":
                functionObject = new CalculateFunction();
                break;
            case "CheckResponse":
                functionObject = new CheckResponseFunction();
                break;
            case "Set_Response":
                functionObject = new Set_ResponseFunction();
                break;
            case "BlockInt":
                functionObject = new BlockIntFunction();
                break;
            case "BlockHexStr":
                functionObject = new BlockHexStrFunction();
                break;
            case "BlockHex":
                functionObject = new BlockHexFunction();
                break;
            case "BlockStr":
                functionObject = new BlockStrFunction();
                break;
            case "IntToStr":
                functionObject = new IntToStrFunction();
                break;
            case "Control_CS":
                functionObject = new Control_CSFunction();
                break;
            case "HexSubStrToHexStr":
                functionObject = new HexSubStrToHexStrFunction();
                break;
            case "HexSubStrToInt":
                functionObject = new HexSubStrToIntFunction();
                break;
            case "ETH_Ini":
                functionObject = new ETHIni();
                break;
            case "IPv4_Ini":
                functionObject = new IPv4_IniFunction();
                break;
            case "UDSRawIni1":
                functionObject = new UDSRawIni1Function();
                break;
            case "UDSRawDown":
                functionObject = new UDSRawDownFunction();
                break;
            case "Eth_Down1":
                functionObject = new Eth_Down1Function();
                break;
            case "Anzahl":
                functionObject = new AnzahlFunction();
                break;
            case "BlockFloat32":
                functionObject = new BlockFloat32Function();
                break;
            case "HexSubStrToFloat32":
                functionObject = new HexSubStrToFloat32();
                break;
            case "FloatToStr":
                functionObject = new FloatToStrFunction();
                break;
            case "StrToHexString_CS":
                functionObject = new StrToHexString_CSFunction();
                break;
            case "Break":
            case "BreakStr_CS":
                functionObject = new BreakFunction();
                break;
            case "ComStop1":
                functionObject = new ComStop1Function();
                break;
            case "Data_AddData_Num":
                functionObject = new DataAddDataNumFunction();
                break;
            case "Data_AddData_Str":
                functionObject = new DataAddDataStrFunction();
                break;
            case "Data_IdPlate":
                functionObject = new DataIdPlateFunction();
                break;
            case "DataWrite":
                functionObject = new DataWriteFunction();
                break;
            default:
                functionObject = new NotImplementedFunction();
        }
        functionObject.setStack(stack);
        return functionObject;
    }

    private boolean testCondition(String presentTC, Set<ETestCondition> givenTC) {
        boolean isValid;

        String giventc = "";
        if (givenTC.isEmpty()) {
            giventc = "";
        } else {
            for (ETestCondition cond : givenTC) {
                giventc += cond.toString().toUpperCase().charAt(0) + ",";
            }
            //remove last coma
            giventc = giventc.substring(0, giventc.length() - 1);
        }
        //CORE LOGIC
        //split presentTestCondition by ','
        String presentTCS[] = presentTC.split(",");
        String givenTCS[] = giventc.split(",");

        //Separating the presentTCS in two lists 1.with "!" charecters 2.without "!" charecters
        List<String> withNotChars = new ArrayList<>();
        List<String> withoutNotChars = new ArrayList<>();

        for (String s : presentTCS) {
            if (s.contains("!")) {
                withNotChars.add(s);
            } else {
                if (!s.isEmpty()) {
                    withoutNotChars.add(s);
                }
            }
        }
        //if test condition is not selected
        //radio buttons replaced with checkbox ,and none is selected
        if (!givenTC.isEmpty()) {
            boolean AND = withNotChars.isEmpty() ? true : processWithNotCharsList(withNotChars, givenTCS);
            boolean OR = true;
            if (AND) {
                OR = withoutNotChars.isEmpty() ? true : processWithoutNotCharsList(withoutNotChars, givenTCS);
            }
            if (AND && OR) {
                isValid = true;
            } else {
                isValid = false;
            }
        } else {
            isValid = true;
        }
        return isValid;
    }

    private static boolean processWithNotCharsList(List<String> withNotChars, String[] givenTC) {
        //!L,!D,!W
        //in W
        boolean out = true;
        List<Boolean> givenTCSData = new ArrayList<>();
        for (String givenTC1 : givenTC) {
            boolean ispresent = processTC(givenTC1, withNotChars);
            if (ispresent) {
                givenTCSData.add(false);
            } else {
                givenTCSData.add(true);
            }
        }
        if (givenTCSData.contains(false)) {
            out = false;
        }
        //out = !givenTCSData.contains(false);
        return out;

    }

    private static boolean processWithoutNotCharsList(List<String> withoutNotChars, String[] givenTC) {
        //P,W
        //in W,V
        boolean out = false;
        List<Boolean> givenTCSData = new ArrayList<>();
        for (String givenTC1 : givenTC) {
            boolean ispresent = processTC(givenTC1, withoutNotChars);
            if (ispresent) {
                givenTCSData.add(true);
            } else {
                givenTCSData.add(false);
            }
        }
        if (givenTCSData.contains(true)) {
            out = true;
        }
        // out = !givenTCSData.contains(false);
        return out;
    }

    private static boolean processTC(String givenTC1, List<String> withNotChars) {
        boolean ispresent = false;
        for (String ch : withNotChars) {
            if (ch.contains(givenTC1)) {
                ispresent = true;
                break;
            } else {
                ispresent = false;
            }
        }
        return ispresent;
    }

    private List<AbstractFunction> processExternalParamsAsSubFunctions(String externalParams) {
        List<AbstractFunction> externalSubFunctions = new ArrayList<>();        //TODO : Generalize this logic
        //handling separately for power supply
        //TODO : not dynamically acceptable
        if (externalParams.contains("UB1.U") || externalParams.contains("UB1.IMAX") || externalParams.contains("Ub1.U") || externalParams.contains("Ub1.IMAX")) {
            //extract only voltage and current
            //assuming first line will be voltage and second line will current

            //Split all externalParams by "\n"
            String[] separatedExternalParams = externalParams.split("\n");

            //TODO : iterate separatedExternalParams to process all parameter
            ExternalParamFunction externalParamFunction = new ExternalParamFunction();

            //check if first parameter/first line is voltage or not by checking its unit
            String voltageName = "";
            if (separatedExternalParams[0].contains("V")) {
                voltageName = separatedExternalParams[0].replace(" ", "").substring(0, separatedExternalParams[0].length() - 1).split("=")[1];
            }
            //check if second parameter/second line is current or not by checking the unit
            String currentName = "";
            if (separatedExternalParams[1].contains("I")) {
                currentName = separatedExternalParams[1].replace(" ", "").substring(0, separatedExternalParams[1].length() - 1).split("=")[1];
            }

            String buildParameters = "globals(),voltage=" + voltageName.substring(0, voltageName.length() - 1).replace("-", "0") + ","
                    + "current=" + currentName.substring(0, currentName.length() - 1).replace("-", "0") + "," + "ps=power_supply";

            externalParamFunction.setName("ConfigPowerSupply");
            externalParamFunction.setParams(buildParameters);

            //adding function in externalParamFunction 
            externalSubFunctions.add(externalParamFunction);

            //to indent extenal params function ,which are comes under control statements
            externalParamFunction.setStack(stack);
        } else if (externalParams.contains(this.deviceConfigurationController.getOnStatus())) {
            //TODO : iterate separatedExternalParams to process all parameter
            ExternalParamFunction externalParamFunction = new ExternalParamFunction();

            externalParamFunction.setName(Constants.POWER_ON);
            externalParamFunction.setParams("command=" + "\"" + this.deviceConfigurationController.getOnStatus() + "\"");

            //adding function in externalParamFunction 
            externalSubFunctions.add(externalParamFunction);

            //to indent extenal params function ,which are comes under control statements
            externalParamFunction.setStack(stack);

        } else if (externalParams.contains(this.deviceConfigurationController.getOffStatus())) {
            //TODO : iterate separatedExternalParams to process all parameter
            ExternalParamFunction externalParamFunction = new ExternalParamFunction();
            externalParamFunction.setName("ps_off = " + Constants.POWER_Off);
            externalParamFunction.setParams("command=" + "\"" + this.deviceConfigurationController.getOffStatus() + "\"");

            //adding function in externalParamFunction 
            externalSubFunctions.add(externalParamFunction);

            //to indent extenal params function ,which are comes under control statements
            externalParamFunction.setStack(stack);
        } else {
            //Split all externalParams by "\n"
            String[] separatedExternalParams = externalParams.split("\n");
            for (String eachParam : separatedExternalParams) {
                ExternalParamFunction externalParamFunction = new ExternalParamFunction();
                if (null != eachParam && !eachParam.isEmpty() && eachParam.contains("=")) {
                    String paramsName = eachParam.split("=")[0].trim();

                    String paramsValue = eachParam.split("=")[1].split(" ")[0];
                    //if ms contain remove it ex : Tw = 100ms ,above logice will not work
                    paramsValue = paramsValue.replace("ms", "").trim();

                    externalParamFunction.setName(paramsName);
                    externalParamFunction.setParams(paramsValue);
                    externalSubFunctions.add(externalParamFunction);

                    //to indent extenal params function ,which are comesunder control statements
                    externalParamFunction.setStack(stack);
                }
            }
        }

        return externalSubFunctions;
    }

}
