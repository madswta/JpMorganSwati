import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;
import java.util.stream.Stream;

/**
 * @Author Swati Madaan
 */
public class JpMorgan {
    /**
     * @param args
     * Method for displaying word occurrences from a text file using serial stream
     * JP Morgan interview question
     * Chose to read all lines at once to reduce number of IO operations
     * Takes 94 ms for processing
     */
    public static void main(String[] args) {
        long timeStart = System.currentTimeMillis();
        final String filePath = "/Volumes/SwatiWorkspace/javapractice/Exercise2/src/main/resources/JpMorgan.txt";
        StringBuilder fileContent = new StringBuilder();
        HashMap<String, Integer> wordMap = new HashMap<>();
        try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
            stream.forEach(s -> fileContent.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Code for word occurrences count
        String[] words = fileContent.toString().replace(".\n", " ").replace(".","").replace(",", "").split(" ");
        Arrays.stream(words).forEach(word -> {
            String wd = word.trim().toLowerCase();
            int count = wordMap.getOrDefault(wd, 0);
            wordMap.put(wd, count + 1);
        });
        long timeEnd = System.currentTimeMillis();
        System.out.println("Displaying Word Occurrences" + wordMap);
        System.out.println("time taken" + (timeEnd - timeStart)); //Time taken by serial stream
    }
}
