package com.ap.stronghold.view.enums.commands;

public class Option {
    private String name;
    private int inputsCount;
    private boolean required;

    public Option(String name, int inputsCount, boolean required) {
        this.name = name;
        this.inputsCount = inputsCount;
        this.required = required;
    }

    public String getName() {
        return name;
    }

    public int getInputsCount() {
        return inputsCount;
    }

    public boolean isRequired() {
        return required;
    }
}
