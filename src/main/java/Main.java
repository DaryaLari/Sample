import TextAnalyzer.MultithreadAnalyzer;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        MultithreadAnalyzer analyzer = new MultithreadAnalyzer();
        analyzer.run();

        long end = System.currentTimeMillis();

        System.out.println("Working time: " + (end - start));
    }
}
