package TextAnalyzer.Patterns;

import java.util.regex.Pattern;

public class Patterns {

    public static final Pattern wordPattern = Pattern.compile("([\\p{L}|\\p{Digit}]+(\\p{Punct}+[\\p{L}|\\p{Digit}]+)*)+");
    /* паттерн слов. Включает в себя:
     числовые диапазоны,
     дробные числа,
     слова, содержащие знаки препинания как составные части слов
     (напр., "какой-то", "Ann's")
      аббревиатуры */

    public static final Pattern delimiterPattern = Pattern.compile("([\\p{Punct}|]*\\s+[\\p{Punct}|]*)+");

    public static final Pattern CyrillicNumericPunctuationPattern = Pattern.compile("[а-я|А-Я|\\d|\\p{P}|\\s]*+");
}
