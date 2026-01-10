import java.util.Queue;
import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class ReadLastK {

    /*
     * Problem: Implement tail -n k
     *
     * The Task:
     *   Write a program that takes a filename and an integer k as input,
     *   and prints the last k lines of the file.
     *
     * Why it's crucial:
     *   Forces you to think about pointers and reading files efficiently
     *   (potentially from the end backwards if the file is huge) rather
     *   than blindly reading everything into a List.
     *
     * Java Focus:
     *   - RandomAccessFile (advanced approach)
     *   - Circular Buffer approach using LinkedList or Queue while reading forward
     */

    public static void main (String[] args) {
        /*
        1.) open file, stream input via Buffered Reader
        2.) iterate through file
            if circular queue size is greater than K, 
                -> remove the 0th element (queue.poll() means remove head)
                -> add to the end of the list (queue.offer() means add to tail)
        */

        int k = 150;
        Queue<String> circularQueue = new LinkedList<>();

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            String line;
            while((line = reader.readLine()) != null) {

                if(circularQueue.size() < k) {
                    circularQueue.offer(line);
                } else {
                    //remove head
                    circularQueue.poll();
                    //add to tail
                    circularQueue.offer(line);
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

       for(String line: circularQueue) {
            System.out.println(line);
       }
        
    }
}
