package TextAnalyzer.Patterns;

import java.util.regex.Pattern;

public class CyrillicNumericPunctuationPatternChecker extends PatternChecker {

    @Override
    public Pattern getPattern() {
        return Patterns.CyrillicNumericPunctuationPattern;
    }
}
