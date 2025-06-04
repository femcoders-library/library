package org.example.util;

public enum AnsiStyle {

    RESET("\u001B[0m"),
    BLACK("\u001B[30m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    MAGENTA("\u001B[35m"),
    CYAN("\u001B[36m"),
    WHITE("\u001B[37m"),

    BG_BLACK("\u001B[40m"),
    BG_RED("\u001B[41m"),
    BG_GREEN("\u001B[42m"),
    BG_YELLOW("\u001B[43m"),
    BG_BLUE("\u001B[44m"),
    BG_MAGENTA("\u001B[45m"),
    BG_CYAN("\u001B[46m"),
    BG_WHITE("\u001B[47m"),

    BOLD("\u001B[1m"),
    UNDERLINE("\u001B[4m");
    
    private final String code;
    
    AnsiStyle(String code) {
        this.code = code;
    }

    public String get() {
        return code;
    }

    public static String stylingText(String text, AnsiStyle... styles) {
        StringBuilder sb = new StringBuilder();
        for (AnsiStyle style : styles) {
            sb.append(style.code);
        }
        return sb + text + RESET.code;
    }
}
