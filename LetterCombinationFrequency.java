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

    public  <K, V extends Comparable<V>> LinkedHashMap<K, V> sortByValue(HashMap<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));
        LinkedHashMap<K, V> sortedMap = new LinkedHashMap<>();
        
        for (Map.Entry<K, V> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        
        return sortedMap;  
    }

    


}
