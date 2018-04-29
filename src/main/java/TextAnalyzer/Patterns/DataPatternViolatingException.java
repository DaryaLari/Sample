package TextAnalyzer.Patterns;

public class DataPatternViolatingException extends Exception {

    public DataPatternViolatingException() {
        super("Pattern of data was violated.");
    }

    public DataPatternViolatingException(String message) {
        super(message);
    }

    public DataPatternViolatingException(String fileName, String line) {
        super(String.format("Pattern of data in file %s was violated. (Line: \"%s\")", fileName, line));
    }
}
