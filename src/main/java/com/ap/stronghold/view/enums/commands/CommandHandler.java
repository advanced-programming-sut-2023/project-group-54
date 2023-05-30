package com.ap.stronghold.view.enums.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandHandler {
    private static Pattern getPattern(Command command) {
        String input = "(\\s+[^\"]\\S*)|(\\s+\"[^\"]+\")";
        String optionsRegex = "";
        for (Option option : command.getOptions()) {
            optionsRegex += (optionsRegex.isEmpty() ? "" : "|") + "(\\s+-" + option.getName() +
                    "(" + input + "){" + option.getInputsCount() + "})";
        }
        if (!optionsRegex.isEmpty()) optionsRegex = "(" + optionsRegex + ")*";
        String regex = "\\s*" + command.getCommandRegex() + optionsRegex + "\\s*";
        return Pattern.compile(regex);
    }

    public static HashMap<String, ArrayList<String>> parsCommand(Command command, String input){
        if(!getPattern(command).matcher(input).matches())
            return null;
        HashMap<String, ArrayList<String>> output = new HashMap<>();
        for (Option option : command.getOptions()) {
            String regex = "\\s+-" + option.getName() + "(?<inputs>((\\s+[^\"]\\S*)|(\\s+\"[^\"]+\")){" + option.getInputsCount() + "})";
            Matcher matcher = Pattern.compile(regex).matcher(input);
            if (!matcher.find()) {
                if (option.isRequired())
                    return null;
                continue;
            }
            Matcher inputsMatcher = Pattern.compile("(?<input>(\\s+[^\"]\\S*)|(\\s+\"[^\"]+\"))").matcher(matcher.group("inputs"));
            ArrayList<String> inputs = new ArrayList<>();
            for(int i = 0; i < option.getInputsCount(); i++){
                inputsMatcher.find();
                inputs.add(inputsMatcher.group("input").trim());
            }
            output.put(option.getName(), inputs);
            if(matcher.find())
                return null;
        }
        return output;
    }
}
