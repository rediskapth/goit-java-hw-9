package ua.goit.task3;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class UniqueWordsCalculator {
    private static final String PATH_TO_FILE = "src/main/resources/Task3/words.txt";

    public static void main(String[] args) {
        Map<String, Integer> wordsCount = new HashMap<>();
        List<String> words = new ArrayList<>();
        List<String> keys = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileReader(PATH_TO_FILE))) {
            while (scanner.hasNext()) {
                words.add(scanner.next());
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        for (String word : words) {
            if (wordsCount.containsKey(word)) {
                wordsCount.put(word, wordsCount.get(word) + 1);
            } else {
                wordsCount.put(word, 1);
            }
        }
        keys.addAll(wordsCount.keySet());
        for (String key : keys) {
            System.out.println(key + " " + wordsCount.get(key));
        }
    }
}
