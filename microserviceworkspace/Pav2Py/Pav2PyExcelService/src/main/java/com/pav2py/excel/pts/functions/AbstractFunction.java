package com.pav2py.excel.pts.functions;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import com.pav2py.excel.utlilty.BasicUtilities;
import com.pav2py.excel.utlilty.Constants;
import com.pav2py.excel.utlilty.EDataType;
import com.pav2py.excel.utlilty.Util;

public abstract class AbstractFunction {


	private String functionName = "";
	private String inputParams = "";
	private String outPutParam = "";
	private String min = "";
	private String max = "";
	private EDataType dimension = null;
	protected Set<String> keysSet;

	Stack<String> stack = new Stack<String>();
	boolean endif=false;

	public boolean getEndif() {
		return endif;
	}

	public void setEndif(boolean endif) {
		this.endif = endif;
	}
	public Stack<String> getStack() {
		return stack;
	}

	public void setStack(Stack<String> stack) {
		this.stack = stack;
	}


	public AbstractFunction() {
		keysSet = new LinkedHashSet<>();
	}

	public String processParams() {
		//replace newlines with " "
		String toString = getParams().trim().replaceAll("\n", " ");

		//remove last semi colon if it exist
		//assuming only one semicolon exist at end 
		if (toString.endsWith(Constants.PARAM_END_CHAR)) {
			toString = toString.substring(0, toString.length() - 1); //remove last semi-colon
		}
		//replace all semicolon with coma 
		//toString = toString.replaceAll(";", ",");

		setParams(toString);
		return "";
	}

	public String buildParameters(Map<String, String> processedInputParams){
		StringBuilder builder = new StringBuilder();
		for (String key : processedInputParams.keySet()) {
			//builder.append(key).append("=");
			builder.append(Util.getProcessedKeyValuePairAsAPassingParameterWithoutHex(key, processedInputParams.get(key)));
			builder.append(",");
		}
		//replace '&' with ""
		String out = builder.toString().replace("&", "");
		out = BasicUtilities.replaceToPyBooleanValue(out);
		out = out.substring(0, out.length() - 1);//remove last comma
		return out;
	} 
	public String buildParametersWithHex(Map<String, String> processedInputParams){
		StringBuilder builder = new StringBuilder();
		for (String key : processedInputParams.keySet()) {
			//builder.append(key).append("=");
			builder.append(Util.getProcessedKeyValuePairAsAPassingParameterWithHex(key, processedInputParams.get(key)));
			builder.append(",");
		}
		//replace '&' with ""
		String out = builder.toString().replace("&", "");
		out = BasicUtilities.replaceToPyBooleanValue(out);
		out = out.substring(0, out.length() - 1);//remove last comma
		return out;
	}

	public String getName() {
		return functionName;
	}

	public void setName(String name) {
		this.functionName = name;
	}

	public String getParams() {
		return inputParams;
	}

	public void setParams(String params) {
		this.inputParams = params;
	}

	public String getOutPutParam() {
		return outPutParam;
	}

	public void setOutPutParam(String outPutParam) {
		this.outPutParam = outPutParam;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	public EDataType getDimension() {
		return dimension;
	}

	public void setDimension(EDataType dimension) {
		this.dimension = dimension;
	}

	public String toPy() {
		return "";
	}

	public String getProcessedParameter(String toString) {

		//REPLACE NEWLINE WITH SPACE
		toString = toString.trim().replaceAll("\n", " ");

		//REMOVE LAST OCCUARANCE OF SEMICOLON,IF IT EXISTS
		//ASSUMING ONLY ONE SEMI COLON PRESENT AT END
		if (toString.endsWith(Constants.PARAM_END_CHAR)) {
			toString = toString.substring(0, toString.length() - 1); //remove last semi-colon
		}
		//REPLACE ALL SEMICOLONS WITH COMMA 
		//toString = toString.replaceAll(";", ",");

		setParams(toString);
		return toString.trim();
	}

	public Map<String, String> processInputParameters(Set<String> keys, String str) {

		/*

        This function process the given string with key set provided
        Inputs : Set = Keys set,String = input String
        Output : String = Processed input String

        Example : 
        Inputs : Keys set =  Transfer_Value,Style_Format
                 input String = Transfer_Value=V110;;==_Q;Style_Format=0www;

        output : Transfer_Value="V110;;==_Q",Style_Format="0www"

		 */
		Map<String, String> pair = new LinkedHashMap<>();

		//remove all the unwantedkeys from list which are available in input string
		Set<String> unwantedkeys = new LinkedHashSet<>();
		for (String key : keys) {
			if (!str.contains(key)) {
				unwantedkeys.add(key);
			}
		}
		//remove all unwantedkeys list from original key list
		keys.removeAll(unwantedkeys);
		if (!keys.isEmpty()) {
			//process input string with present keys
			while (!"".equals(str)) {
				str = str.trim();
				String keyName = "";
				String keyValue = "";
				for (String key : keys) {
					//check if string starts with any key present in the list
					if (str.trim().startsWith(key)) {
						keyName = key;

						//process and extract the string until next key encounters
						keyValue = findValueTillNextKeyFound(str, keys, key);
						keyValue = keyValue.trim();
						//check if last char is semi colon
						//assuming only one colon exist at the end 
						String lastChar = keyValue.substring(keyValue.length() - 1, keyValue.length());
						String processKeyValue = ((lastChar.equals(Constants.PARAM_END_CHAR)) ? keyValue.substring(0, keyValue.length() - 1) : keyValue).trim();

						//put key value pair in the map
						processKeyValue = processKeyValue.substring(processKeyValue.indexOf("=") + 1, processKeyValue.length());
						pair.put(keyName, processKeyValue);
						break;
					}
				}
				//remove processed keyvalue pair from main string
				str = str.substring(keyValue.length(), str.length());
			}
		}else{
			pair =  null;
		}

		return pair;
	}

	private String findValueTillNextKeyFound(String str, Set<String> keys, String key) {
		/*
        This function finds value of key till next key encounters
        Inputs : Set = Keys set, String = Input String
        Output : String = value

        Example : 
        Inputs : Keys set =  Transfer_Value,Style_Format
                 input String = Transfer_Value=V110;;==_Q;Style_Format=0www;

        output : "V110;;==_Q" and "0www"

		 */
		String out = "";
		for (int i = 0; i < keys.size(); i++) {
			//if list in contains only one key value pair return as it is witout any process
			if (keys.size() == 1) {
				out = str;
				break;
			} else {
				//find the next occurance of the key in the string
				//if insertion order of keys in keys set is changed then this line will help to find next immediate key
				//Order is not changed ,we can directly access the next key from set
				String nextKey = nextKey(str, keys, key);
				if (str.contains(nextKey)) {
					//extract string from 0 to next key 
					out = str.substring(0, str.indexOf(nextKey));
					break;
				} else {
					out = str;
				}
			}
		}
		return out;
	}

	private String nextKey(String str, Set<String> keys, String key) {
		/*
        This function find the immediate occuarance of the key in the string
        Inputs : Set = Keys set, String=Input String, String=key
        Output : String = nextKey

        Example : 
        Inputs : Keys set =  Transfer_Value,Style_Format
                 input String = Transfer_Value=V110;;==_Q;Style_Format=0www;

        output : String = Style_Format
		 */
		String out;
		//if keys size is not equal to 1 ,then only remove key from list
		if (keys.size() != 1) {
			keys.remove(key);
		}

		//map is used to find first occurance of key in the list
		//store keys with there index value in the map
		Map<Integer, String> map = new HashMap<>();
		for (String str2 : keys) {
			map.put(str.indexOf(str2), str2);
		}

		//find min index and return corresponding value ,as it is first occurance of the key in the string
		int min = Collections.min(map.keySet());
		out = map.get(min);
		return out;
	}

	public String buildIfCondtion(String type, String condition) {

		StringBuilder builder = new StringBuilder();
		//Type will be mandatory parameter
		builder.append(type).append("(");

		String modifiedCondition = condition.replace("||", "or").replace("&&", "and").replace("(", "").replace(")", "").replace("&G_NERR", "Functions.G_NERR");
		//check if any constant/variable present
		if (modifiedCondition.startsWith("Functions.G_NERR")||Util.validate(modifiedCondition) != EDataType.INVALID) {
			builder.append(modifiedCondition);
		} else {
			builder.append("\"").append(modifiedCondition.replace("&", "")).append("\"");
		}
		builder.append(")").append(":");
		return builder.toString().replace("&", "");
	}

}
