package string_processing.assigment_problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StopWordFilteredWordFrequencyReport {

    static void generateReport(String paragraph) {
        if (paragraph == null || paragraph.trim().isEmpty()) {
            return;
        }
        String cleaned = paragraph.toLowerCase().replace(".", "").replace(",", "");
        String[] words = cleaned.trim().split("\\s+");
        HashMap<String, Integer> freq = new HashMap<>();
        String[] stopWords = {"the", "was", "and", "a", "is", "of", "in"};
        for (int i = 0; i < words.length; i++) {
            String w = words[i].trim();
            if (w.isEmpty()) {
                continue;
            }
            boolean isStop = false;
            for (int j = 0; j < stopWords.length; j++) {
                if (w.equals(stopWords[j])) {
                    isStop = true;
                    break;
                }
            }
            if (isStop) {
                continue;
            }
            if (freq.containsKey(w)) {
                freq.put(w, freq.get(w) + 1);
            } else {
                freq.put(w, 1);
            }
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<>(freq.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
                return b.getValue().compareTo(a.getValue());
            }
        });
        for (Map.Entry<String, Integer> e : list) {
            System.out.println(e.getKey() + ": " + e.getValue());
        }
    }

    public static void main(String[] args) {
        String para = "The mentor was great, the session was great\nand clear.";
        generateReport(para);
    }
}

