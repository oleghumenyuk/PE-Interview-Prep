import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class TopErrors {

    /*
     * Here is the problem stripped down to a pure algorithmic challenge.
     *
     * Problem: Most Frequent Error Code (Time Window)
     *
     * Difficulty: Medium
     *
     * Description:
     * You are given a list of strings logs, where each string is formatted as "error_code:timestamp".
     *   - error_code is a string consisting of alphanumeric characters.
     *   - timestamp is an integer representing time in seconds.
     *
     * The logs array is guaranteed to be sorted by timestamp in ascending order.
     *
     * Your task is to return the error_code that appears most frequently in the last 10 minutes
     * (600 seconds) of the logs.
     *
     * Definition of Window:
     * The "last 10 minutes" is defined relative to the timestamp of the last element in the input array.
     *   - Let T_last be the timestamp of the last log.
     *   - The valid window is inclusive: [T_last - 600, T_last].
     *   - Any log with a timestamp strictly less than T_last - 600 must be ignored.
     *
     * Returns:
     *   - Return the most frequent error_code as a String.
     *   - If there is a tie, return any one of the most frequent codes.
     *   - If the input is empty, return null.
     *
     * Example 1:
     *   Input: logs = [
     *     "DB_FAIL:100",
     *     "TIMEOUT:150",
     *     "DB_FAIL:650",   // Inside window
     *     "TIMEOUT:700",   // Inside window
     *     "TIMEOUT:750"    // Inside window (Last Log)
     *   ]
     *   Output: "TIMEOUT"
     *
     * Explanation:
     *   - Last Log Time: 750.
     *   - Window Start: 750 - 600 = 150.
     *   - Valid Range: [150, 750].
     *   - Logs in Window:
     *       - TIMEOUT:150 (Included)
     *       - DB_FAIL:650 (Included)
     *       - TIMEOUT:700 (Included)
     *       - TIMEOUT:750 (Included)
     *   - Ignored: DB_FAIL:100 (Timestamp 100 < 150).
     *   - Counts: TIMEOUT=3, DB_FAIL=1.
     *   - Result: "TIMEOUT".
     *
     * Example 2 (Tie):
     *   Input: logs = [
     *     "404:1000",
     *     "500:1005"
     *   ]
     *   Output: "404" (or "500")
     *
     * Explanation: Both fall within the window [405, 1005]. Both have count 1.
     *
     * Constraints:
     *   - 1 <= logs.length <= 10^5
     *   - Timestamps are non-negative integers.
     */
    public static void main(String[] args) {

        List<LogEntry> errors = new ArrayList<>();

        // read in file
        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            String line;
            while((line = reader.readLine()) != null) {
                errors.add(new LogEntry(line));
            }
        } catch (IOException ex) { 
            ex.printStackTrace();
        }

        // store only last 10 minutes
        List<LogEntry> logs = getLastTenMin(errors.reversed());
        //System.out.println(logs.toString());


        // go through last 10 minutes of errors and store them in HashMap<ErrorCode, # times seen>
        HashMap<String, Integer> counts = getCounts(logs);
        System.out.println(counts.toString());

        // iterate through them all and return MAX
        HashMap.Entry<String, Integer> MAX = counts.entrySet().iterator().next();
        

        for(HashMap.Entry<String, Integer> errorSummary: counts.entrySet()) {
            if(MAX.getValue() < errorSummary.getValue()) {
                MAX = errorSummary;
            }
        }

        System.out.println(MAX.toString());

    }

    public static HashMap<String, Integer> getCounts(List<LogEntry> errors) {
        HashMap<String, Integer> counts = new HashMap<>();

        for(LogEntry log: errors) {
            if(counts.containsKey(log.errorName)) {
                int count = counts.get(log.errorName);
                counts.put(log.errorName, count + 1);
            } else {
                counts.put(log.errorName, 1);
            }
        }


        return counts;
    } 

    public static List<LogEntry> getLastTenMin(List<LogEntry> errors) {
        ArrayList<LogEntry> results = new ArrayList<>();

        results.add(errors.getFirst());
        Long now = results.getFirst().unixTimeStamp;
        
        //need stop time (now - 600)
        Long stopTime = now - 600;

        for(int i = 1; i < errors.size(); i++) {
            if(errors.get(i).unixTimeStamp < stopTime) {
                return results;
            } else {
                results.add(errors.get(i));
            }
        }

        return results;
    }

    static class LogEntry {
        String errorName;
        Long unixTimeStamp;

        public LogEntry(String line) {
            String[] parsed = line.split(":");
            this.errorName = parsed[0];
            this.unixTimeStamp = Long.parseLong(parsed[1].trim());
        }

        public String toString() {
            return "Error: " + this.errorName + " occured @ " + this.unixTimeStamp;
        }
    }
}