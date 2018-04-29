package TextAnalyzer.WordProcessors.UniquenessCheckers;

import TextAnalyzer.Patterns.Patterns;
import TextAnalyzer.WordProcessors.WordsProcessor;

import java.util.HashMap;
import java.util.Map;

public class WordUniquenessChecker implements WordsProcessor {

    protected Map<String, String> dictionary = new HashMap<>()/*ConcurrentHashMap<>()*/;

    @Override
    public boolean processWord(String word){
        if(word.matches(Patterns.wordPattern.pattern())) {
            return addWord(word);
        }
        return true;
    }

    protected synchronized boolean addWord(String word){
        String prevValue = dictionary.putIfAbsent(word.toLowerCase(), word);
        return (prevValue == null); // false если слово уже было
    }

}
