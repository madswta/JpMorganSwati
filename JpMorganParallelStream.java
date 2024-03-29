import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Stream;

/**
 * @Author Swati Madaan
 */
public class JpMorganParallelStream {
        /**
         * @param args
         * Method for displaying word occurrences from a text file using parallel stream
         * JP Morgan interview question
         * Chose to read all lines at once to reduce number of IO operations
         */
        public static void main(String[] args) {
            long timeStart = System.currentTimeMillis();
            final String filePath = "/Volumes/SwatiWorkspace/javapractice/Exercise2/src/main/resources/JpMorgan.txt";
            StringBuilder content = new StringBuilder();
            HashMap<String, Integer> wordMap = new HashMap<>();
            try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
                stream.forEach(s -> content.append(s).append("\n"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] words = content.toString().replace(".\n", " ").replace(".","").replace(",", "").split(" ");
            Arrays.stream(words).parallel().forEach(word -> {
                String wd = word.trim();
                int count = wordMap.getOrDefault(wd.trim(), 0);
                wordMap.put(wd, count + 1);
            });
            long timeEnd = System.currentTimeMillis();
            System.out.println(wordMap);
            System.out.println(timeEnd - timeStart); //Time taken by parallel stream
        }
    }


