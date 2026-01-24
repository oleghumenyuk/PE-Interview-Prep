public class KSortedLogs {

    /*
     * Problem: Merge K Sorted Logs (LeetCode #23 variant)
     *
     * Given 3 large log files (server1.log, server2.log, server3.log), each
     * already sorted by timestamp locally, write a Java program that reads
     * all 3 files and prints a single, globally sorted stream to stdout.
     *
     * Example:
     *   Input:
     *     server1.log: [09:00] A, [09:05] B
     *     server2.log: [09:01] C, [09:06] D
     *     server3.log: [09:03] E
     *
     *   Output:
     *     [09:00] A
     *     [09:01] C
     *     [09:03] E
     *     ...
     *
     * Hint: Use a helper class to store {Timestamp, LineContent, SourceFile}
     * so you know which file to pull from next after popping from the PriorityQueue.
     */
    public static void main(String[] args) {

    }
}
