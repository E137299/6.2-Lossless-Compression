import java.util.*;
import java.io.*;

public class Compression {
    private HashMap<String, Integer> twoLetterFreq;
    private HashMap<String, Integer> threeLetterFreq;

    public Compression() {
        twoLetterFreq = new HashMap<>();
        threeLetterFreq = new HashMap<>();
    }

    // Method to process text from a file and update the frequency maps
    public void processFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;

        while ((line = reader.readLine()) != null) {
            line = line.replaceAll("[^a-zA-Z]", "").toLowerCase();  // Remove non-letter characters
            calculateCombinations(line);
        }
        reader.close();
    }

    // Method to calculate two- and three-letter combinations
    private void calculateCombinations(String text) {
        for (int i = 0; i < text.length() - 1; i++) {
            // Two-letter combinations
            String twoLetter = text.substring(i, i + 2);
            twoLetterFreq.put(twoLetter, twoLetterFreq.getOrDefault(twoLetter, 0) + 1);

            // Three-letter combinations (ensure enough characters remain)
            if (i < text.length() - 2) {
                String threeLetter = text.substring(i, i + 3);
                threeLetterFreq.put(threeLetter, threeLetterFreq.getOrDefault(threeLetter, 0) + 1);
            }
        }
    }

    // Method to sort and print a frequency map by values
    private void printSortedFrequencies(HashMap<String, Integer> map, String title) {
        List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(map.entrySet());
        sortedList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));  // Sort by value descending

        System.out.println("\n" + title + " (Sorted by Frequency):");
        for (Map.Entry<String, Integer> entry : sortedList) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    // Method to print both two- and three-letter frequencies
    public void printFrequencies() {
        printSortedFrequencies(twoLetterFreq, "Two-Letter Combinations Frequency");
        printSortedFrequencies(threeLetterFreq, "Three-Letter Combinations Frequency");
    }
}
