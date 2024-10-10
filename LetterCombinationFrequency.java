import java.util.*;
import java.io.*;


public class LetterCombinationFrequency {
    private HashMap<String, Integer> twoLetterFreq;
    private HashMap<String, Integer> threeLetterFreq;
    private HashMap<String, Integer> fourLetterFreq;
    private HashMap<String, Integer> fiveLetterFreq;
    private HashMap<String, String> compressionMap;

    public LetterCombinationFrequency() {
        twoLetterFreq = new HashMap<>();
        threeLetterFreq = new HashMap<>();
        fourLetterFreq = new HashMap<>();
        fiveLetterFreq = new HashMap<>();
        compressionMap = new HashMap<>();
    }

    public String readFileToString(String fileName) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    


}
