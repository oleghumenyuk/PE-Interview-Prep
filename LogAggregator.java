import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LogAggregator {

    /*
     * The Challenge: "The Log Aggregator"
     *
     * Input:
     *   A stream of raw log lines. Some are garbage, some are valid.
     *   Valid format: [TIMESTAMP] [STATUS] [MESSAGE]
     *
     * Example:
     *   [12:00:01] ERROR Connection reset
     *   [12:00:02] INFO Started job
     *   junk data
     *   [12:00:03] ERROR Timeout
     *   [12:00:03] INFO Completed
     *
     * The Ask:
     *   Write a function that parses the logs and returns the Top 2 most frequent ERROR messages.
     *
     * The "Meta" Twist (Why this trips people up):
     *   1. Garbage Handling: Did you crash on the line "junk data"? (Fail).
     *   2. String Parsing: Did you handle the brackets [] correctly or just hack it with split?
     *   3. Data Structure: Did you use a Dictionary/HashMap to count?
     *   4. Sorting: Can you get the "Top N" efficiently (Heap vs. Sorting)?
     */
    public static void main(String[] args) {

        /*
        1.) Open File and use BufferedReader to read in input line by line

        2.) Process line
            -> Apply Logic
                -> String.split("//s") by whitespace
                    -> if String[1] is ERROR
                            -> then process the rest of [n] chars as part of error message
                                -> put the error message in a HashMap<"Error Message", Frequency Count>

        
        3.) Once we have the frequencies
            go through each entry and put in priority queue (size 2)
                -> if queue is over 2, remove the least element
        

        */

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, Integer> counts = new HashMap<>();

        try {

            String line;
            while((line = bufferedReader.readLine()) != null) {

                String[] logEntry = line.split("\\s+");

                if(isErrorMessage(logEntry)) {
                    String errorMessage = parseErrorMessage(logEntry);

                    if(counts.containsKey(errorMessage)) {
                        int newCount = counts.get(errorMessage) + 1;
                        counts.put(errorMessage, newCount);
                    } else {
                        counts.put(errorMessage, 1);
                    }
                }

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        System.out.println(counts.toString());

        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
            (a,b) -> a.getValue() - b.getValue()
        );

        for(Map.Entry<String, Integer> entry: counts.entrySet()) {
            pq.offer(entry);
            if(pq.size() > 2) {
                pq.poll();
            }
        }

        System.out.println(pq.toString());
    }

    public static String parseErrorMessage(String[] logEntry) {
        StringBuilder sb = new StringBuilder();

        //start building error message

        for(int i = 2; i < logEntry.length; i++) {
            sb.append(logEntry[i]);
            sb.append(" ");
        }
        return sb.toString();
    }

    public static boolean isErrorMessage(String[] logEntry) {
        if(logEntry.length < 3) {
            return false;
        }

        if(logEntry[1].equals("ERROR")) {
            return true;
        }
        
        return false;
    }
}
