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
       System.out.println("solve it!!!");

    }
}
