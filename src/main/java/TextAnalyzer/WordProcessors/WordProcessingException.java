package TextAnalyzer.WordProcessors;

public abstract class WordProcessingException extends Exception {

    public WordProcessingException() {
        super();
    }


    public WordProcessingException(String message) {
        super(message);
    }
}
