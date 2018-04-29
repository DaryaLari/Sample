package TextAnalyzer;

import TextAnalyzer.Patterns.CyrillicNumericPunctuationPatternChecker;
import TextAnalyzer.Patterns.PatternChecker;
import TextAnalyzer.WordProcessors.UniquenessCheckers.WordUniquenessChecker;
import TextAnalyzer.WordProcessors.WordsProcessor;

public class MultithreadAnalyzer {

    PatternChecker patternChecker = new CyrillicNumericPunctuationPatternChecker();

    WordsProcessor wordsProcessor = new WordUniquenessChecker();

    Reader[] readers;

    public void run(){

        FileNamesReader fnr = new FileNamesReader();
        String[] paths = fnr.readFromConsole();

        readers = new Reader[paths.length];
        for (int i = 0; i < paths.length; i++) {
            readers[i] = new Reader(paths[i], patternChecker, wordsProcessor);
            readers[i].start();
        }

        for (Thread t : readers){
            try{
                t.join();
            }
            catch (InterruptedException e){
                Thread.currentThread().interrupt();
                System.out.println("!!! Thread " + t.getId() + " has been terminated.");
            }
        }
    }

    public void setWordsProcessor(WordsProcessor wp){
        wordsProcessor = wp;

        for (Reader t : readers){
                t.setWordsProcessor(wordsProcessor);
        }
    }
}
