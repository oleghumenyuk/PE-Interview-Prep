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
        System.out.println("Test!");

    }
}
