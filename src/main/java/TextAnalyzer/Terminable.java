package TextAnalyzer;

public interface Terminable extends Runnable {

    void terminateGroup();
    void terminateInstance();
}
