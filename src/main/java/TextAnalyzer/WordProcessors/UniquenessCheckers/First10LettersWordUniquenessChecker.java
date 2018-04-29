package TextAnalyzer.WordProcessors.UniquenessCheckers;

import java.util.HashMap;
import java.util.Map;

public class First10LettersWordUniquenessChecker extends WordUniquenessChecker {

    protected Map<String, String> dictionary = new HashMap<>()/*ConcurrentHashMap<>()*/;

    @Override
    protected synchronized boolean addWord(String word){
        String prevValue = dictionary.putIfAbsent(word.toLowerCase().substring(0, 9), word);
        return (prevValue == null); // false если слово уже было
    }
}
