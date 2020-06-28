package pl.sda;

import java.util.HashMap;
import java.util.Map;

public enum Answer {
    YES("Tak"),
    RATHER_YES("Raczej tak"),
    NO("Nie"),
    RATHER_NO("Raczej nie");

    private final String displayText;
    private static final Map<String, Answer> map = new HashMap<>();

    Answer(String displayText){
        this.displayText = displayText;
    }

    public String getDisplayText() {
        return displayText;
    }

    static {
        for (Answer answer : Answer.values()) {
            map.put(answer.displayText, answer);
        }
    }

    @Override
    public String toString(){
        return displayText;
    }

    public static Answer valueOfDisplayText(String displayText) {
        return map.get(displayText);
    }

}
