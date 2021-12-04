package com.npkompleet.receipt;

import java.util.regex.Pattern;

public class InputValidator {
    /**
     * Takes an input line from the input file and checks
     * if it is valid.
     * 
     * @param inputLine A single line from the input file.
     * @return The validity of the input line.
     */
    public static boolean isValidInput(String inputLine) {
        String[] wordArray = inputLine.trim().split(" "); // Trim line before split in case of trailing whitespace

        if (wordArray.length < 4 || wordArray.length > 7) {
            return false;
        }

        if (!(isValidNumber(wordArray[0]) && isValidNumber(wordArray[wordArray.length - 1]))) {
            return false;
        }

        if (!wordArray[wordArray.length - 2].equals("at")) {
            return false;
        }
        return true;
    }

    private static boolean isValidNumber(String nbrString) {
        Pattern numberPattern = Pattern.compile("\\d+(\\.\\d+)?");
        return numberPattern.matcher(nbrString).matches();
    }
}
