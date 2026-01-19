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

    }
}
