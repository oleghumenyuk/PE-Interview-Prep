import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BalancedPartition {

    /*
     * Problem: Balanced Partition
     *
     * A balanced partition is a split of an array into two parts (without reordering
     * any elements) such that the sum of numbers before the split equals the sum of
     * numbers after the split.
     *
     * Task:
     *   You are given a file containing N arrays of integers, one array per line,
     *   where each array is represented as comma-separated integers.
     *   For each array in the file, if the array can be partitioned in a balanced way,
     *   return the balanced partitions.
     *
     * Example Input (file.txt):
     *   1,2,3,4
     *   1,4,5
     *
     * Expected Output:
     *   [[1,4], [5]]
     *
     * Note: The first line (1,2,3,4) has no valid partition, so no output.
     *       The second line (1,4,5) splits at: [1,4] (sum=5) and [5] (sum=5).
     */

    public static void main(String[] args) {

        //read in file
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {

            String line;
            while((line = bufferedReader.readLine()) != null) {
                processLine(line);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void processLine(String line) {
        //convert to array
        String[] strings = line.split(",");

        
        int[] ints = new int[strings.length];
        
        for(int i = 0; i < strings.length; i++) {
            
            try {

                int convertedValue;
                convertedValue = Integer.parseInt(strings[i]);
                ints[i] = convertedValue;

            

            } catch (Exception ex) {
                System.out.println("invalid line");
                return;
            }
        }

        checkPartition(ints);
    }

    public static void checkPartition(int[] nums) {
        if(nums.length == 0) {
            System.out.println("[]");
            return;
        }

        /*
        * Example Input (file.txt):
     *   1,2,3,4
     *   1,4,5
     *
     * Expected Output:
     *   [[1,4], [5]]
        */

        int window1Start = 0;
        int window1End = nums.length - 2;
        int window1Sum = 0;


        int window2Start = nums.length - 1;
        int window2End = nums.length - 1;
        int window2Sum = 0;

        //get window1 value

        for(int i = window1Start; i <= window1End; i++) {
            window1Sum += nums[i];
        }

        //get window2 value

         for(int i = window2Start; i <= window2End; i++) {
            window2Sum += nums[i];
        }

        while(window1Start < window2Start) {

        //if they are balanced: return two arrays
        if(window1Sum == window2Sum) {
            int[] leftPart = Arrays.copyOfRange(nums, window1Start, window1End + 1);
            int[] rightPart = Arrays.copyOfRange(nums, window2Start, window2End + 1);
            System.out.println("[" + Arrays.toString(leftPart) + ", " + Arrays.toString(rightPart) + "]");
        }

        //if they are not, window1End --, window2Start --
        window1Sum -= nums[window1End];
        window2Sum += nums[window1End];
        window1End--;
        window2Start--;
        }
        
        

        
    }
}