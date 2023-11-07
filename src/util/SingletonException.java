package util;

public class SingletonException extends Exception {
    private String singletonString;
    private int occurrenceIndex;

    public SingletonException(String singletonString, int occurrenceIndex) {
        this.singletonString = singletonString;
        this.occurrenceIndex = occurrenceIndex;
    }

    @Override
    public String getMessage() {
        return singletonString + " is a singleton string that is found at index " + occurrenceIndex + "!";
    }
}
