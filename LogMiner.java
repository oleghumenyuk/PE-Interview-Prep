import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LogMiner {

    /*
     * ### Exercise 1: The "Log Miner" (Text Manipulation & Aggregation)
     *
     * *Concept:* Simulates analyzing server logs to find issues.
     *
     * - **Input:** A file path to a text file. Each line follows the format: `[TIMESTAMP] [IP_ADDRESS] [STATUS_CODE] [ENDPOINT]`.
     *     - Example: `162345 192.168.1.1 500 /api/v1/login`
     * - **Task:**
     *     1. Parse the file.
     *     2. Identify which IP address has generated the most `500` (Internal Server Error) status codes.
     *     3. **Constraint:** The file might be 50GB (cannot load into memory).
     * - **Key Skills:** `BufferedReader`, `HashMap<String, Integer>`, String splitting, custom Object/Class creation if needed.
     * - **Edge Cases to Handle:** Malformed lines (missing fields), non-integer status codes.
     */
    public static void main(String[] args) {
        /*

        ### 2. The "Log Miner" (Aggregation)

        **Problem:** Top K Frequent IP Addresses

        - **The Task:** You are given a massive log file (10GB+). 
        Each line is: `[TIMESTAMP] [IP_ADDRESS] [STATUS]`. 
        162345 192.168.0.1 200 /api/v1/login

        
        Find the top 5 IP addresses that returned a `500` error.
        - **Why it’s crucial:** PE roles live in logs. This tests `HashMap` usage and memory management (you can't load the whole file at once).
        - **Java Focus:** `Files.lines()`, `HashMap<String, Integer>`, `PriorityQueue` (for "Top K").

        plan
        Read logs one by one via BufferedReader
        162345 192.168.0.1 200 /api/v1/login

        */

        HashMap<String, Integer> statuses = new HashMap<>();

        // open logs
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("server_logs.txt"))) {

            String line;
            while((line = bufferedReader.readLine()) != null ) {
                IpStatus lineStatus = processLine(line);

                if (lineStatus.valid && lineStatus.statusCode.equals("500")) {
                    if(!statuses.containsKey(lineStatus.ipAddress)) {
                        statuses.put(lineStatus.ipAddress,1);
                    } else {
                        int currentCount = statuses.get(lineStatus.ipAddress);
                        statuses.put(lineStatus.ipAddress, currentCount + 1);
                    }
                }


            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }


        //setup up PQ
        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(
            new Comparator<Map.Entry<String, Integer>>() {
                public int compare(Map.Entry<String, Integer> first, Map.Entry<String, Integer> second) {
                    return first.getValue() - second.getValue();
                }
            } 
        );


       

       for(Map.Entry<String, Integer> entry: statuses.entrySet()) {
        minHeap.offer(entry);

        //if more than 5, we need to drop the smallest
        if(minHeap.size() > 5) {
            minHeap.poll();
        }
       }

       //results of the top 5
       System.out.println(minHeap.toString());

    }

    public static IpStatus processLine(String logLine) {
        return new IpStatus(logLine);
    }


    public static class IpStatus {
        String ipAddress;
        String statusCode;
        boolean valid;

        public IpStatus(String logLine) {
            String[] convertedLogLine = logLine.split("\\s+");

            if(convertedLogLine.length >= 3 && convertedLogLine.length <= 4) {
                this.ipAddress = convertedLogLine[1];
                this.statusCode = convertedLogLine[2];
                this.valid = true;
            } else {
                this.valid = false;
            }
        }
    }
    
}
