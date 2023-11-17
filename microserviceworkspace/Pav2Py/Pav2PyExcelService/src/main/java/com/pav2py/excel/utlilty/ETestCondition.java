package com.pav2py.excel.utlilty;

public enum ETestCondition {

	 LAB("L"), PRODUCTION("P"), WARRANTY("W"), QZ("Q"), VERIFY("V"), DEVELOPMENT("D");

    private final String value;

    private ETestCondition(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ETestCondition getEnumFromString(String name) {
        for (ETestCondition testCondition : ETestCondition.values()) {
            if (testCondition.name().equalsIgnoreCase(name)) {
                return testCondition;
            }
        }
        return null;
    }

}

