import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

public class RateLimiter {

    /*
     * Implement a Rate Limiter function.
     * Given a user ID and a timestamp, return true if the request is allowed
     * (e.g., max 5 requests per minute), false otherwise.
     * Java Focus: HashMap<String, Deque<Long>> (Sliding window log).
     */
    public static void main(String[] args) {

        //open file and read in input

        //process line
            //NOW becomes the timestamp
            //if user 
        
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, Deque<Long>> timestamps = new HashMap<>();

        try {

            String line;
            while((line = bufferedReader.readLine()) != null) {

                String[] userData = line.split("\\s+");
                String userID = userData[0];
                Long timeStamp = Long.parseLong(userData[1]);

                Long now = timeStamp;

                if(timestamps.containsKey(userID)) {

                    //if less than 5 requests for the user ... just process the request
                    if(timestamps.get(userID).size() < 5) {
                        Deque<Long> stack = timestamps.get(userID);
                        stack.push(timeStamp);

                        timestamps.put(userID, stack);
                        System.out.println(userID + "made valid request at " + userData[1]);
                    } else {
                        //means there are 5 requests or more in the stack for the user
                        Deque<Long> stack = timestamps.get(userID);
                        
                        //is the top of the stack less than 1 min -> return false
                        if(now - stack.peekLast() < 60) {
                            System.out.println("RATE LIMITED");
                            System.out.println(userID + "made invalid request at " + userData[1]);
                        } else {
                            //we need to throw out any time outside of the 60 second window
                            while(!stack.isEmpty() && now - stack.peekLast() >= 60) {
                                stack.removeLast();
                            }

                            stack.add(timeStamp);
                            timestamps.put(userID, stack);
                            System.out.println(userID + "made valid request at " + userData[1]);
                        }
                    }
                } else {
                    Deque<Long> stack = new ArrayDeque<>();
                    stack.push(timeStamp);

                    timestamps.put(userID, stack);
                    System.out.println(userID + "made valid request at " + userData[1]);
                }


            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}