package TextAnalyzer.Patterns;

import java.util.regex.Pattern;

public abstract class PatternChecker {

    public abstract Pattern getPattern();

    public boolean checkForPatternMatching(String str) {
        return str.matches(getPattern().pattern());
    }
}
