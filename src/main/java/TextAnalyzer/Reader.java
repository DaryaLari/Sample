package TextAnalyzer;

import TextAnalyzer.Patterns.PatternChecker;
import TextAnalyzer.Patterns.Patterns;
import TextAnalyzer.WordProcessors.WordsProcessor;

import java.io.*;
import java.util.regex.Pattern;

public class Reader extends Thread implements Terminable{

    protected String fileName;
    protected int MAX_BUFFER_SIZE = 1024;
    protected WordsProcessor wordsProcessor;
    protected PatternChecker patternChecker;

    protected boolean shouldThreadTerminate = false;
    protected static volatile boolean shouldGroupTerminate = false;

    public Reader(String fileName, PatternChecker patternChecker, WordsProcessor wordsProcessor) {
        this.fileName = fileName;
        this.patternChecker = patternChecker;
        this.wordsProcessor = wordsProcessor;
    }

    public void setWordsProcessor(WordsProcessor wp){
        wordsProcessor = wp;
    }

    @Override
    public void run(){
        String nextWord = "";
        try (FileInputStream fis = new FileInputStream(fileName);
             BufferedReader bs = new BufferedReader(new InputStreamReader(fis, "UTF-8")))
        {

            char[] buffer = new char[MAX_BUFFER_SIZE];

            int charsRead;
            while ((charsRead = bs.read(buffer, 0, MAX_BUFFER_SIZE)) != -1 && !shouldTerminate()){
                String ln = nextWord + new String(buffer, 0, charsRead);
                /* если последнее слово не поместилось полностью в предыдущий буфер, то присоединяем ту часть к новой строке */

                if(!patternChecker.checkForPatternMatching(ln)){
                    terminateGroup();
                    System.out.println(String.format("\n\n!!!!!\nThread of file \'%s\' violated pattern in \'%s\' \n!!!!!\n\n", fileName, ln));
                }
                if(shouldTerminate()){
                    System.out.println(String.format("Thread of file \'%s\' terminated", fileName));
                    return;
                }
                String[] tokens = ln.split(Patterns.delimiterPattern.pattern(), -1);

                for (int i = 0; i < tokens.length - 1; i++) { /* последнее слово обработаем со следующей строкой (мб это только часть) */
                    if(shouldTerminate()){
                        System.out.println(String.format("Thread of file \'%s\' terminated", fileName));
                        return;
                    }
                    nextWord = tokens[i];
                    processToken(nextWord);
                }
                nextWord = tokens[tokens.length - 1];
            }
            processToken(nextWord);

            System.out.println(String.format("Thread of file \'%s\' terminated", fileName));
            return;
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    protected void processToken(String word){

        if(!shouldTerminate()) {
            System.out.println(String.format("thread of file \'%s\' is working... Analyze word \'%s\'", fileName, word));
            if(!wordsProcessor.processWord(word)){ // с нарушением условий
                System.out.println(String.format("\n\n!!!!!\nThread of file \'%s\' found dublicate word \'%s\' \n!!!!!\n\n", fileName, word));
                terminateGroup();
            }

        }
    }

    @Override
    public synchronized void terminateGroup() {
        shouldGroupTerminate = true;
    }

    @Override
    public void terminateInstance() {
        shouldThreadTerminate = true;
    }

    protected boolean shouldTerminate(){
        return shouldGroupTerminate || shouldThreadTerminate;
    }

}
