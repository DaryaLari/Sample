package TextAnalyzer.WordProcessors.UniquenessCheckers;

import TextAnalyzer.WordProcessors.WordProcessingException;

public class UniquenessViolationException extends WordProcessingException {

    public UniquenessViolationException() {
        super("Repeated word found.");
    }

    public UniquenessViolationException(String repeatedWord) {
        super(String.format("Repeated word \'%s\' was found.", repeatedWord));
    }

    public UniquenessViolationException(String repeatedWord, long threadId) {
        super(String.format("Repeated word \'%s\' was found in thread %d.", repeatedWord, threadId));
    }
}
