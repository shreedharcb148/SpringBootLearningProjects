package com.pav2py.excel.utlilty;

import org.apache.poi.ss.usermodel.Row;

public class Constants {


    public final static Row.MissingCellPolicy isRowNull = Row.MissingCellPolicy.RETURN_BLANK_AS_NULL;
    public final static String NVM_FILE_LOCATION = "\\\\bosch.com\\dfsrb\\DfsDE\\DIV\\CS\\DE_CS$\\Prj\\BaseDev\\DASy\\08_Plant\\0500_AnP_CljP\\1100_PAV2Py-Converter\\0400_TestEnvironment\\DASy\\BASE\\NVM";
    public final static String NVM_VARIANTSHEET_NAME = "NVM";

    //NVM File headers
    public final static String NVMConstantHeader = "Constant";
    public final static String NVMDescriptionHeader = "Description";
    public final static String NVMECU_ValuesHeader = "DUT1_Value";
    public final static String NVMBoschnumberHeader = "Boschnumber";
    public final static String NVMDUT_Variant_NumberHeader = "DUT_Variant_Number";
    public final static String NVMDUT_Variant_DescriptionHeader = "DUT_Variant_Description";
    public final static String NVMCompare_ValuesHeader = "compare values";
    public final static String NVMSW_ContainerHeader = "SW-Container";
    public final static String NVMPTSHeader = "PTS";

   //CFI2DEVICE DATA
    public static final String APP_HOME_DEVICE_CONFIGURATION_FXML = "/fxmls/DeviceConfigurationFXML.fxml";
    public static final String DEVICE_VALIDATION_SUCCESS_MSG="Device configuration validation for CF12/CF12+HW successful";
    public static final String DEVICE_VALIDATION_FAILED_MSG="Device configuration validation for CF12/CF12+HW unsuccessful. Please select the unique DIP ID values";



    //PTS file sheets
    public final static String PTSGT_Numbers = "GT_Numbers";
    public final static String PTSGT_Constants = "GT_Constants";
    public final static String PTSGT_Variables = "GT_Variables";
    public final static String PTSTG_EOL = "TG_EOL";


    //GT_Numbers sheet header values
    public final static String PTSVariantSheetConstantHeader = "Constant";
    public final static String PTSVariantSheetDecriptionHeader = "Description";
    public final static String PTSVariantSheetECU_ValuesHeader = "DUT1_Value";
    public final static String PTSVariantSheetECU_Variant_NumberHeader = "DUT_Variant_Number";
    public final static String PTSVariantSheetECU_Variant_DescriptionHeader = "DUT_Variant_Description";

    //PTSGT_Constants sheet header values
    public final static String PTSConstantSheetConstantHeader = "Constant";
    public final static String PTSConstantSheetValueHeader = "Value";
    public final static String PTSConstantSheetDescriptionHeader = "Description";

    //PTSVariable sheet header values
    public final static String PTS_VARIABLESHEET_VARIABLE_HEADER = "Variable";
    public final static String PTS_VARIABLESHEET_SIZE_HEADER = "String_Length";
    public final static String PTS_VARIABLESHEET_ARRAYSIZE_HEADER = "Array_Size";
    public final static String PTS_VARIABLESHEET_DESCRIPTION_HEADER = "Description";

    //PTS TG_EOL Sheet header values
    public final static String PTS_TG_SHEET_ROWTYPE_HEADER = "Row_Type";
    public final static String PTS_TG_SHEET_POSITION_HEADER = "Position";
    public final static String PTS_TG_SHEET_DUT_VARIANT_HEADER = "DUT_Variants";
    public final static String PTS_TG_SHEET_TEST_TYPE_HEADER = "Test_Types";
    public final static String PTS_TG_SHEET_EXTERNAL_PARAMETERS_HEADER = "External_Parameters";
    public final static String PTS_TG_SHEET_MEASUREMENT_OBJECT_HEADER = "Measurement_Object";
    public final static String PTS_TG_SHEET_FUNCTION_HEADER = "Function";
    public final static String PTS_TG_SHEET_INPUT_PARAMETERS_HEADER = "Input_Parameters";
    public final static String PTS_TG_SHEET_OUTPUT_PARAMETERS_HEADER = "Output_Parameter";
    public final static String PTS_TG_SHEET_MIN_VALUE_HEADER = "Min_Value";
    public final static String PTS_TG_SHEET_MAX_VALUE_HEADER = "Max_Value";
    public final static String PTS_TG_SHEET_DIMENSION_HEADER = "Dimension";
    public final static String PTS_TG_SHEET_DESCRIPTION_HEADER = "Description";

    public final static String PTSSheetCellColor = "FFFF0000";
    public final static String PTSCLCellColor = "FFC0C0C0";
    public final static String PTSTG_EOLRowChapType = "Chap";
    public final static String PTSTG_EOLRowCLType = "CL";

    public final static String str_type = "str";
    public final static String hex_type = "hex";
    public final static String int_type = "int";
    public final static String uint_type = "uint";
    public final static String I32_TYPE = "i32";
    public final static String UI32_TYPE = "ui32";
    public final static String I64_TYPE = "i64";
    public final static String UI64_TYPE = "UI64";
    public final static String F64_TYPE = "f64";
    public final static String HANDLE_TYPE = "handle";
    public final static String STRUCT_TYPE = "std_strct";
    public final static String FILE_TYPE = "file";
    public final static String MS_TYPE = "ms";
    

    public final static String separator = "_";
    public final static String fontColor = "FFFF0000";
    public final static String fileExtension = "*.xlsx";

    public final static String EXCEL_EXTENSION = ".xlsx";

    public final static String constantStartKey = "&c";
    public final static String variableStartKey = "&";
    public final static String GLOBAL_VARIABLE_KEY = "&g_";

    public final static String PTS_VERSION_PREFIX = "V";
    public final static String FILE_SEPARATOR = System.getProperty("file.separator");

    public final static String BASE_PTS_PATH = "PTS";
    public final static String PARAM_END_CHAR = ";";

    public final static String CSV_REPORTS_FOLDER_NAME = "Reports";
   // public final static String STR_CAT_FUNCTION_NAME = "StrCat_CS";

    public final static String SET_VARIABLE_FUNCTION_NAME= "Set_Variable";
     //Used for serialization and deserialization of CAN data
    public final static String DEVICE_XML_FILE="C:/Users/Public/Roaming/Pav2Py/DeviceConfiguration.xml";
    public final static String DEVICE_CONFIGURATIONS_DIR = "C:/Users/Public/Roaming/Pav2Py/Store-Device-Data"; 
    public final static String MES_CONFIGURATIONS_DIR = "C:/Users"; 
     public final static String DEVICE_XML_SCHEMA="C:/Users/Public/Roaming/Pav2Py/xsd/DeviceModelschema.xsd";
     public final static String DEVICE_DEFAULT_XML_FILE_PATH="C:/Users/Public/Roaming/Pav2Py/Default-Device-Data/DefaultDeviceConfiguration.xml";
    
//    public final static String STR_COMPARE_FUNCTION_NAME = "StringCompare";
//    public final static String STR_COPY_FUNCTION_NAME = "StringCopy";
//    public final static String STR_COMPARE_CS_FUNCTION_NAME = "StrCmp_CS";
//    public final static String STR_COPY_CS_FUNCTION_NAME = "StrCopy_CS";
      public final static String CALCULATE_FUNCTION_NAME = "Calculate";
//    public final static String CHECKRESPONSE_FUNCTION_NAME = "CheckResponse";
//    public final static String BLOCKINT_FUNCTION_NAME = "BlockInt";
//    public final static String BLOCKHEXSTR_FUNCTION_NAME = "BlockHexStr";
//    public final static String BLOCKHEX_FUNCTION_NAME = "BlockHex";
//    public final static String BLOCKSTR_FUNCTION_NAME = "BlockStr";
      public final static String WAITUNITL1_FUNCTION_NAME = "WaitUntil1";
//    public final static String INT_TO_STR_FUNCTION_NAME = "IntToStr";
      public final static String CONTROL_CS_FUNCTION_NAME = "Control_CS";
//    public final static String HexSubStr_To_HexStr_FUNCTION_NAME = "HexSubStrToHexStr";
//    public final static String HexSubStr_To_Int_FUNCTION_NAME="HexSubStrToInt";
//    public final static String Anzahl_FUNCTION_NAME = "Anzahl";
//    public final static String HexSubStr_To_Float32_FUNCTION_NAME = "HexSubStrToFloat32";
//    public final static String BlockFloat32_FUNCTION_NAME = "BlockFloat32";
//    public final static String FloatToStr_FUNCTION_NAME="FloatToStr";
//    public final static String StrToHexString_CS_FUNCTION_NAME="StrToHexString_CS";
//    public final static String Break_FUNCTION_NAME="Break";
//    public final static String COMBLOCK3_FUNCTION_NAME="ComBlock3";
//    public final static String COMBLOCK2_FUNCTION_NAME="ComBlock2";
//    public final static String CAN2000Ini4_FUNCTION_NAME="CAN2000Ini4";
//    public final static String CANFD2000Ini1_FUNCTION_NAME="CANFD2000Ini1";
//    public final static String CANFD2000Ini_FUNCTION_NAME="CANFD2000Ini";
//    public final static String ETH_Ini_FUNCTION_NAME="ETH_Ini";
//    public final static String IPv4_Ini_FUNCTION_NAME="IPv4_Ini";
//    public final static String UDSRawIni1_FUNCTION_NAME="UDSRawIni1";
//    public final static String UDSRawDown_FUNCTION_NAME="UDSRawDown";
//    public final static String Eth_Down1_FUNCTION_NAME="Eth_Down1";
//    public final static String COMSTOP_FUNCTION_NAME="ComStop1";
//    public final static String Break_CS_FUNCTION_NAME="BreakStr_CS";
//    public final static String Data_AddDataNum_FUNCTION_NAME="Data_AddData_Num";
//    public final static String Data_AddDataStr_FUNCTION_NAME="Data_AddData_Str";
//    public final static String Data_IdPlate_FUNCTION_NAME="Data_IdPlate";
//    public final static String Data_Write_FUNCTION_NAME="DataWrite";
    public final static String HELP_FILES_LOC = "C:/Users/Public/Roaming/Pav2Py/Docs";
    public final static String POWER_ON="SwitchOnPowerSupply";
    public final static String POWER_Off="SwitchOffPowerSupply";
    public final static String SINGLE_TAB="\t";
    public final static String DOUBLE_TAB="\t\t";
    public final static String TRIPLE_TAB="\t\t\t";
   
    
} 
