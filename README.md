# 6.2 Lossless Compression: Heuristic Compression Algorithm with Frequency Evaluation

**Objective:**  
You will create a heuristic compression algorithm that uses emojis to represent words or combinations of letters, focusing on frequently used letter combinations to maximize compression. The program should be capable of compressing text and decompressing the compressed text.



**What is a Heuristic?**  
A heuristic is a problem-solving approach that employs a practical method or shortcut to find an approximate solution efficiently, even if it may not be perfect. In this assignment, you will develop a heuristic to identify patterns in text and compress it based on frequency of occurrence.

---

**Full Instructions:** [refer to previous detailed assignment content]

#### Instructions:

1. **Setup**: 
   - Download the three presidential speeches provided.
   - Feel free to choose additional texts that use less formal language.

2. **Implementation**:
   - Create a Java program that reads in the text from the provided files.

3. **Frequency Evaluation**:
   - Use a **HashMap** to implement a method to evaluate the frequency of words and letter combinations (two-letter, three-letter, and four-letter) from the text.
   - Use this frequency data to inform which words or combinations to replace with emojis in your compression algorithm.

4. **Training**:
   - Test your algorithm on various texts to determine its effectiveness in compression.
   - Evaluate how well it performs on an unknown text that will be provided for grading.

4. **Compression Algorithm**:
   - Use a **HashMap** to associate common words or phrases with emojis, allowing for efficient storage and retrieval.
   - **Compress Method**: This method should replace frequent words or letter combinations with their corresponding emojis.
   - **Decompress Method**: This should reverse the compression by replacing emojis back with the original words or combinations.



**HashMap Explanation**:
   - A HashMap in Java is a collection that stores key-value pairs, allowing for fast retrieval of values based on their keys. It is particularly useful for implementing associative arrays.
      - For example, when conducting frequency analysis, a letter combo could be the key and the number of times it appears in the text could be the value.
      - For example, in your compression algorithm, the emoji could be the key and the word it represents could be the value.
   - For more information on using HashMaps in Java, visit the W3Schools tutorial: [Java HashMap Tutorial](https://www.w3schools.com/java/java_hashmap.asp).


