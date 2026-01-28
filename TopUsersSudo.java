import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopUsersSudo {

    /*
     * 2. Parse a raw text file containing executed command history:
     *    `[timestamp] user: command`.
     *    Find the top 5 users who ran `sudo`.
     *
     * Java Focus: String parsing logic combined with HashMap.
     */

    public static void main(String[] args) {

        HashMap<String, Integer> sudoCounts = new HashMap<>();

        try {

            //open file
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while((line = bufferedReader.readLine()) != null) {
            String[] logData = line.split("\\s+");
            //logData[3] must be sudo
            if(logData[3].equals("sudo")) {
                String user = logData[2];

                if(sudoCounts.containsKey(user)) {
                    int previousTimes = sudoCounts.get(user);
                    sudoCounts.put(user, previousTimes + 1);

                } else {
                    sudoCounts.put(user, 1);
                }
            }
        }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        //after we process logs, we store top 5 users in min heap
        //if min heap is over 5, we remove the lowest user


        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
            (a, b) -> a.getValue() - b.getValue()
        );

        for(Map.Entry<String, Integer> entry : sudoCounts.entrySet()) {
            pq.add(entry);

            if(pq.size() > 5) {
                pq.poll();
            }
            
        }

        System.out.println(pq.toString());

    }


}
