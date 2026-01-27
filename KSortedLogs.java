import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.PriorityQueue;

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

        BufferedReader server1Reader;
        BufferedReader server2Reader; 
        BufferedReader server3Reader;
        PriorityQueue<LogEntry> pq = new PriorityQueue<>(Comparator.comparing(entry -> entry.time));

        try {
        server1Reader = new BufferedReader(new FileReader("server1.log"));
        server2Reader = new BufferedReader(new FileReader("server2.log"));
        server3Reader = new BufferedReader(new FileReader("server3.log"));

        pq.add(new LogEntry(server1Reader.readLine(), server1Reader));
        pq.add(new LogEntry(server2Reader.readLine(), server2Reader));
        pq.add(new LogEntry(server3Reader.readLine(), server3Reader));

        } catch(Exception ex) {
            ex.printStackTrace();
        }

        while(!pq.isEmpty()) {
            //pick out smallest item
            LogEntry current = pq.poll();


            //print smallest item
            System.out.println(current);

            //read in next item from that file
            try {
                String nextLine = current.fileReader.readLine();
                if (nextLine == null ) {
                    continue;
                }
                pq.add(new LogEntry(nextLine, current.fileReader));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
            

        }

    }

    static class LogEntry {
        LocalDateTime time;
        String content;
        BufferedReader fileReader;

        public LogEntry(String line, BufferedReader source) {
            String[] data = line.split("\\s+");

            time = LocalDateTime.parse(data[0]);
            content = data[1];
            fileReader = source;
        }

        public String toString() {
            return time.toString() + " content: " + content;
        }
    }
}
