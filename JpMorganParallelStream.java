import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Author Swati Madaan
 */
public class JpMorganParallelStream {
    /**
     * @param args Method for displaying word occurrences from a text file using parallel stream / multi threaded environment
     *             JP Morgan interview question
     *             Chose to read all lines at once to reduce number of IO operations
     *             Took 98 ms for processing sample file.
     *             Took a bit more time due to synchronization. Synchronization is required for removing race condition of concurrent hash map
     *             and giving accurate result
     */
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        ConcurrentHashMap<String, Integer> wordOccurrences = new ConcurrentHashMap<>();
        final String filePath = "/Volumes/SwatiWorkspace/javapractice/Exercise2/src/main/resources/JpMorgan.txt";
        Path file = Paths.get(filePath);

        //Code for reading lines and counting words
        try {
            Files.readAllLines(file).parallelStream()
                    .map(line -> line.replace("\n", " ").replace(".", "").replace(",", "").split(" ")).flatMap(Arrays::stream).parallel()
                    .forEach(word -> {
                        String wd = word.trim().toLowerCase();
                        synchronized(wordOccurrences) {
                            Integer ctr = (wordOccurrences.get(wd) == null) ? 0 :wordOccurrences.get(wd);
                            ctr++;
                            wordOccurrences.put(wd, ctr);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("Displaying word occurrences" + wordOccurrences);
        System.out.println("Time taken" + (end - start));
    }
}


