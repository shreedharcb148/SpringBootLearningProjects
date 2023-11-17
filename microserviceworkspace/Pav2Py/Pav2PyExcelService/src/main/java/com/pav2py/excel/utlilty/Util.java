package com.pav2py.excel.utlilty;

import java.util.LinkedHashSet;
import java.util.Set;


public class Util {
	public static EDataType getDataType(String type) {

		if (type.toLowerCase().equals(Constants.hex_type)) {
			return EDataType.HEX;
		}
		if (type.toLowerCase().equals(Constants.str_type)) {
			return EDataType.STRING;
		}

		//adding ms for waitunitl1
		if (type.toLowerCase().equals(Constants.MS_TYPE)) {
			return EDataType.ms;
		}
		if (type.toLowerCase().equals(Constants.int_type)) {
			return EDataType.INT;
		} else {
			return EDataType.INVALID;
		}
	}

	public static EDataType validate(String value) {

		if (value.toLowerCase().startsWith(Constants.GLOBAL_VARIABLE_KEY + Constants.hex_type) || value.startsWith(Constants.variableStartKey + Constants.hex_type) || value.startsWith(Constants.constantStartKey + Constants.hex_type)) {
			return EDataType.HEX;
		}
		if (value.toLowerCase().startsWith(Constants.GLOBAL_VARIABLE_KEY + Constants.str_type) || value.startsWith(Constants.variableStartKey + Constants.str_type) || value.startsWith(Constants.constantStartKey + Constants.str_type)) {
			return EDataType.STRING;
		}
		if (value.toLowerCase().startsWith(Constants.GLOBAL_VARIABLE_KEY + Constants.int_type) || value.startsWith(Constants.variableStartKey + Constants.int_type) || value.startsWith(Constants.constantStartKey + Constants.int_type)) {
			return EDataType.NUMBER;
		}
		if (value.toLowerCase().startsWith(Constants.GLOBAL_VARIABLE_KEY + Constants.I32_TYPE) || value.startsWith(Constants.variableStartKey + Constants.I32_TYPE) || value.startsWith(Constants.constantStartKey + Constants.I32_TYPE)) {
			return EDataType.i32;
		}
		if (value.toLowerCase().startsWith(Constants.GLOBAL_VARIABLE_KEY + Constants.UI32_TYPE) || value.startsWith(Constants.variableStartKey + Constants.UI32_TYPE) || value.startsWith(Constants.constantStartKey + Constants.UI32_TYPE)) {
			return EDataType.ui32;
		}
		if (value.toLowerCase().startsWith(Constants.GLOBAL_VARIABLE_KEY + Constants.I64_TYPE) || value.startsWith(Constants.variableStartKey + Constants.I64_TYPE) || value.startsWith(Constants.constantStartKey + Constants.I64_TYPE)) {
			return EDataType.i64;
		}
		if (value.toLowerCase().startsWith(Constants.GLOBAL_VARIABLE_KEY + Constants.UI64_TYPE) || value.startsWith(Constants.variableStartKey + Constants.UI64_TYPE) || value.startsWith(Constants.constantStartKey + Constants.UI64_TYPE)) {
			return EDataType.ui64;
		}
		if (value.toLowerCase().startsWith(Constants.GLOBAL_VARIABLE_KEY + Constants.F64_TYPE) || value.startsWith(Constants.variableStartKey + Constants.F64_TYPE) || value.startsWith(Constants.constantStartKey + Constants.F64_TYPE)) {
			return EDataType.f64;
		}
		if (value.toLowerCase().startsWith(Constants.GLOBAL_VARIABLE_KEY + Constants.HANDLE_TYPE) || value.startsWith(Constants.variableStartKey + Constants.HANDLE_TYPE) || value.startsWith(Constants.constantStartKey + Constants.HANDLE_TYPE)) {
			return EDataType.handle;
		}
		if (value.toLowerCase().startsWith(Constants.GLOBAL_VARIABLE_KEY + Constants.FILE_TYPE) || value.startsWith(Constants.variableStartKey + Constants.FILE_TYPE) || value.startsWith(Constants.constantStartKey + Constants.FILE_TYPE)) {
			return EDataType.file;
		}
		if (value.toLowerCase().startsWith(Constants.GLOBAL_VARIABLE_KEY + Constants.STRUCT_TYPE) || value.startsWith(Constants.variableStartKey + Constants.STRUCT_TYPE) || value.startsWith(Constants.constantStartKey + Constants.STRUCT_TYPE)) {
			return EDataType.struct;
		}
		if (value.toLowerCase().startsWith(Constants.GLOBAL_VARIABLE_KEY + Constants.uint_type) || value.startsWith(Constants.variableStartKey + Constants.uint_type) || value.startsWith(Constants.constantStartKey + Constants.uint_type)) {
			return EDataType.NUMBER;
		} else {
			return EDataType.INVALID;
		}
	}

	public static EDataType validateConstant(String value) {
		if (value.startsWith(Constants.constantStartKey + Constants.hex_type)) {
			return EDataType.HEX;
		}
		if (value.startsWith(Constants.constantStartKey + Constants.str_type)) {
			return EDataType.STRING;
		}
		if (value.startsWith(Constants.constantStartKey + Constants.int_type)) {
			return EDataType.NUMBER;
		}
		if (value.startsWith(Constants.constantStartKey + Constants.uint_type)) {
			return EDataType.NUMBER;
		} else {
			return EDataType.INVALID;
		}
	}

	public static boolean checkForNumericValue(String string) {
		boolean isNumeric = true;
		//check numeric value presence
		if (!string.matches("^\\d+$")) {
			isNumeric = false;
		}
		return isNumeric;
	}






	public static boolean checkForNumericFloatingValue(String string) {
		boolean isNumeric = true;
		//check numeric value presence
		if (!string.matches("^([+-]?\\d*\\.?\\d*)$")) {
			isNumeric = false;
		}
		return isNumeric;
	}

	public static boolean checkForHexValue(String string) {
		//7E0
		//7e0
		boolean hexPresent = true;
		string = string.toUpperCase();
		for (int i = 0; i < string.length(); i++) {
			char ch = string.charAt(i);
			// Check if the character is invalid
			if ((ch < '0' || ch > '9')
					&& (ch < 'A' || ch > 'F')) {
				hexPresent = false;
				break;
			}
		}
		return hexPresent;
	}

	public static boolean checkForBooleanValue(String string) {

		boolean isBoolean = true;
		//check numeric value presence
		if (!string.toUpperCase().matches("TRUE") && !string.toUpperCase().matches("FALSE")) {
			isBoolean = false;
		}
		return isBoolean;
	}

	public static String hexFormatter(String string) {
		return "format(" + string + ",\"x\").upper()";
	}

	public static String addMultilineComments(String description) {
		if (!description.isEmpty() && description.contains("\n")) {
			return "'''" + description.replace("\n", Util.addNewLineInWindowsStyle()) + "'''" + Util.addNewLineInWindowsStyle();
		}
		return "";
	}

	public static Set<String> splitStringToLinkedHashSet(String string, String sep) {
		Set<String> keySet = new LinkedHashSet<>();
		String[] splittedString = string.split(sep);
		for (String eachStr : splittedString) {
			keySet.add(eachStr.trim());
		}
		return keySet;
	}

	public static String getNumberofTabs(int n) {

		String out = "";
		for (int i = 0; i < n; i++) {
			out += "\t";
		}
		return out;
	}

	public static String addNewLineInWindowsStyle() {
		return "\r\n";
	}

	public static String getProcessedKeyValuePairAsAPassingParameterWithHex(String key, String value) {
		String output = key + "=";

		if (key.equalsIgnoreCase("Interface")|| value.equals("G_STR_PTS_NAME_VERSION")
				|| value.equals("G_STR_NVM_NAME_VERSION") || value.equals("G_UI32_DUT_VARIANT_NUMBER")
				|| Util.checkForNumericValue(value) || Util.checkForBooleanValue(value)
				|| Util.checkForNumericFloatingValue(value) || Util.validate(value) != EDataType.INVALID) {
			output += value;
		} else if (Util.checkForHexValue(value)) {
			//no need to format it as we are passing direct value
			output += "0x" + value;

		}else if(value.equals("&G_NERR")){
			//output+="globals()['G_NERR']";
			output += "Functions.G_NERR";
		} 
		else {
			output += "\"" + value + "\"";
		}

		return output;
	}

	public static String getProcessedKeyValuePairAsAPassingParameterWithoutHex(String key, String value) {
		String output = key + "=";
		if(key.equalsIgnoreCase("DrawingNumber")){
			output += "\"" + value + "\"";
		}
		else if (key.equalsIgnoreCase("Interface") || value.equals("G_STR_PTS_NAME_VERSION") || key.equalsIgnoreCase("Logic_Channel")
				|| value.equals("G_STR_NVM_NAME_VERSION") || value.equals("G_UI32_DUT_VARIANT_NUMBER")
				|| Util.checkForNumericValue(value) || Util.checkForBooleanValue(value)
				|| Util.checkForNumericFloatingValue(value) || Util.validate(value) != EDataType.INVALID) {
			output += value;
		} else if (value.equals("&G_NERR")) {
			output += "Functions.G_NERR";
		}  

		else {
			output += "\"" + value + "\"";
		}

		return output;
	}



	public static boolean isPredefinedConstant(String value){
		if ( value.equals("&G_NERR") || value.equals("G_STR_PTS_NAME_VERSION")
				|| value.equals("G_STR_NVM_NAME_VERSION") || value.equals("G_UI32_DUT_VARIANT_NUMBER")){
			return true;
		}else
			return false;

	}
	public static String checkForG_NERRAndReadItWithGlobals(String value){
		String output = "";
		if(value.equals("&G_NERR")){
			output += "Functions.G_NERR";
		}
		return  output;
	}
}
