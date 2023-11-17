//Copyright (C) Robert Bosch GmbH 2022. All rights reserved.
package com.pav2py.excel.pts.model;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.pav2py.excel.model.abstraction.AbstractWorkBook;
import com.pav2py.excel.utlilty.Constants;
import com.pav2py.excel.utlilty.ETestCondition;
import com.pav2py.excel.utlilty.Util;

/**
 * Model for NVM book
 *
 * @author PJA7COB
 */
public class PTSBook extends AbstractWorkBook {

    private PTSVariantSheet variants;
    private ConstantSheet constants;
    private VariableSheet variables;
    private TestGateSheet testSheet;
    private String selectedTestGate;
    private ETestCondition seleTestCondition;
    private int selectedNVMECUVariant;

    private Set<ETestCondition> testCondns;
    
//    private DeviceConfigurationController deviceConfigurationController;
//
//    public DeviceConfigurationController getDeviceConfigurationController() {
//        return deviceConfigurationController;
//    }
//
//    public void setDeviceConfigurationController(DeviceConfigurationController deviceConfigurationController) {
//        this.deviceConfigurationController = deviceConfigurationController;
//    }
    
    public PTSBook(String name, String path, Workbook workbook, int selectedNVMECUVariant) {
        super(name, path, workbook);
        this.selectedNVMECUVariant = selectedNVMECUVariant;
    }

    public Sheet intitializeSheet(String ptsSheet) throws FileNotFoundException, IOException {

        InputStream input = new FileInputStream(new File(super.getPath()));
        Workbook workbook = new XSSFWorkbook(input);
        //Sheet sheet = workbook.getSheet(ptsSheet);

        return workbook.getSheet(ptsSheet);
    }

    public PTSVariantSheet getVariants() {
        return variants;
    }

    public void setVariants(PTSVariantSheet variants) {
        this.variants = variants;
    }

    public ConstantSheet getConstants() {
        return constants;
    }

    public void setConstants(ConstantSheet constants) {
        this.constants = constants;
    }

    public VariableSheet getVariables() {
        return variables;
    }

    public void setVariables(VariableSheet variables) {
        this.variables = variables;
    }

    public TestGateSheet getExecutionSheet() {
        return testSheet;
    }

    public void setExecutionSheet(TestGateSheet testSheet) {
        this.testSheet = testSheet;
    }

    public String getSelectedTestGate() {
        return selectedTestGate;
    }

    public void setSelectedTestGate(String selectedTestGate) {
        this.selectedTestGate = selectedTestGate;
    }

    public ETestCondition getSeleTestCondition() {
        return seleTestCondition;
    }

    public void setSeleTestCondition(ETestCondition seleTestCondition) {
        this.seleTestCondition = seleTestCondition;
    }

    public TestGateSheet getTestSheet() {
        return testSheet;
    }

    public void setTestSheet(TestGateSheet testSheet) {
        this.testSheet = testSheet;
    }

    public Set<ETestCondition> getTestCondns() {
        return testCondns;
    }

    public void setTestCondns(Set<ETestCondition> testCondns) {
        this.testCondns = testCondns;
    }

    public List<String> getPrettyPrintPTSData() {
        List<String> prettyPrint = new ArrayList<>();
        prettyPrint.add("##############################################################");
        prettyPrint.add("#Selected Test gate : " + getSelectedTestGate());
        prettyPrint.add("#Selected Test Condition : " + getTestCondns().toString());
        prettyPrint.add("#ECU Variant : " + String.valueOf(variants.getSelectedVariant().getEcuVariant()));
        prettyPrint.add("#ECU Variant description : " + variants.getSelectedVariant().getEcuVariantDescription());
        prettyPrint.add("##############################################################");
        prettyPrint.add(Util.addNewLineInWindowsStyle());
        return prettyPrint;
    }

    public List<String> getAllModulesImport(List<String> pyLines,String MESLocation) {
        //append import statements
        pyLines.add("from " + getConstants().getName() + " import *");
        pyLines.add("from " + getVariables().getName() + " import *");
        pyLines.add("from " + getVariants().getName() + " import *");

        pyLines.add("import os");
        pyLines.add("from BeatPav2Py.Functions import *");
        pyLines.add("from math import *");
        pyLines.add("import traceback");
        pyLines.add("import BeatPav2Py.Functions as Functions");
        pyLines.add("from datetime import datetime");
        
        //added global variables section
        pyLines.add(Util.addNewLineInWindowsStyle());
        pyLines.add("#global variables");
        pyLines.add("G_STR_PTS_NAME_VERSION = "+"\""+this.getName().split("\\.")[0]+"\"");
        pyLines.add("Mes_File_Loc=" + "\"" + (MESLocation != null ? MESLocation : "") + "\"");
        pyLines.add("cust_var_dict={}");
        pyLines.add("#mes_data_section=\"default\"");
        pyLines.add("mes_data_section=\"values\"");
        pyLines.add("");
        pyLines.add("def get_cust_var_dict():");
        pyLines.add("\t return cust_var_dict");
        
        pyLines.add(Util.addNewLineInWindowsStyle());
        
        return pyLines;
    }
    

    @Override
    public boolean validateAndInit() {

        if (validateAndInitPTSVariants() && validateAndInitPTSConstants() && validateAndInitPTSVariables() && validateAndInitPTSTestGate()) {
            super.setValid(true);
        }
        return super.isValid();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean validateAndInitPTSVariants() {
        boolean validFlag = false;
        Sheet sheet = super.getWorkbook().getSheet(Constants.PTSGT_Numbers);
        if (null != sheet) {
            variants = new PTSVariantSheet(Constants.PTSGT_Numbers, sheet);
            if (variants.validate()) {
                validFlag = variants.checkIfNVMVariantExist(selectedNVMECUVariant);
            }
        }
        if (!validFlag) {
            super.setValidationMessage("Either " + Constants.PTSGT_Numbers + " sheet is not found or " + Constants.PTSGT_Numbers + " sheet is not in required format or selected ECU variant is not found in " + Constants.PTSGT_Numbers + " sheet");
        }
        super.setValid(validFlag);
        return validFlag;
    }

    private boolean validateAndInitPTSConstants() {
        boolean validFlag = false;
        Sheet sheet = super.getWorkbook().getSheet(Constants.PTSGT_Constants);
        if (null != sheet) {
            constants = new ConstantSheet(Constants.PTSGT_Constants, sheet);
            if (constants.validate()) {
                validFlag = true;
            }
        }
        if (!validFlag) {
            super.setValidationMessage("Either Constants sheet is not found or not in required format");
        }
        super.setValid(validFlag);
        return validFlag;
    }

    private boolean validateAndInitPTSVariables() {
        boolean validFlag = false;
        Sheet sheet = super.getWorkbook().getSheet(Constants.PTSGT_Variables);
        if (null != sheet) {
            variables = new VariableSheet(Constants.PTSGT_Variables, sheet);
            if (variables.validate()) {
                validFlag = true;
            }
        }
        if (!validFlag) {
            super.setValidationMessage("Either Variables sheet is not found or not in required format");
        }
        super.setValid(validFlag);
        return validFlag;
    }

    private boolean validateAndInitPTSTestGate() {
        boolean validFlag = false;
        Sheet sheet = super.getWorkbook().getSheet(selectedTestGate);
        if (null != sheet) {
            testSheet = new TestGateSheet(selectedTestGate, sheet, selectedNVMECUVariant, testCondns);
            if (testSheet.validate()) {
                validFlag = true;
            }
        }
        if (!validFlag) {
            super.setValidationMessage("Either Test gate sheet is not found or not in required format");
        }
        super.setValid(validFlag);
        return validFlag;
    }

    @Override
    public void createSheets() {
        if (super.isValid()) {
            try {
                //MasterLogger.getInstance().logInformation(Constants.Constants.PTSGT_Numbers + " sheet processing...");
                variants.createRows();
                variants.setSelectVariant(selectedNVMECUVariant);
                //MasterLogger.getInstance().logInformation(Constants.Constants.PTSGT_Numbers + " sheet processed successfully...\n");

            } catch (Exception e) {
                //MasterLogger.getInstance().logError("Unable to process " + Constants.Constants.PTSGT_Numbers + "..." + e.getMessage());
            }
            try {
                //MasterLogger.getInstance().logInformation(Constants.Constants.PTSGT_Constants + " sheet processing...");
                constants.createRows();
                //MasterLogger.getInstance().logInformation(Constants.Constants.PTSGT_Constants + " sheet processed successfully...\n");

            } catch (Exception e) {
                //MasterLogger.getInstance().logError("Unable to process " + Constants.Constants.PTSGT_Constants + "..." + e.getMessage());
            }
            try {
                //MasterLogger.getInstance().logInformation(Constants.Constants.PTSGT_Variables + " sheet processing...");
                variables.createRows();
                //MasterLogger.getInstance().logInformation(Constants.Constants.PTSGT_Variables + " sheet processed successfully...\n");

            } catch (Exception e) {
                //MasterLogger.getInstance().logError("Unable to process " + Constants.Constants.PTSGT_Variables + "..." + e.getMessage());
            }
//            try {
//                //MasterLogger.getInstance().logInformation(Constants.PTSTG_EOL + " sheet processing...");
//                if (deviceConfigurationController != null) {
//                    testSheet.setDeviceConfigurationController(deviceConfigurationController);
//                } else {
//                    testSheet.setDeviceConfigurationController(null);
//                }
//                testSheet.createRows();
//                //MasterLogger.getInstance().logInformation(Constants.PTSTG_EOL + " sheet processed successfully...\n");
//            } catch (Exception e) {
//                //MasterLogger.getInstance().logError("Unable to process " + Constants.PTSTG_EOL + "..." + e.getMessage());
//            }
        }
    }

  
  
}
