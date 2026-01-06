import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PidChecker {

    /*
     * Exercise 2: The "Process Manager" (External Systems)
     *
     * Concept: Simulates a script that checks process health.
     *
     * Input: A list of Process IDs (PIDs) provided via Stdin (standard input), one per line.
     *
     * Task:
     *   1. Read PIDs from Stdin.
     *   2. For each PID, check if it is "alive" (simulate with even/odd or mock isAlive(int pid)).
     *   3. If it is NOT alive, print "Restarting [PID]" to Stdout.
     *
     * Key Skills: System.in, Scanner or BufferedReader(new InputStreamReader(System.in)), output formatting.
     */

    public static void main(String[] args) {


        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {

            String line;
            while((line = bufferedReader.readLine()) != null) {
                Integer id = Integer.parseInt(line);
                if(id % 2 != 0) {
                    System.out.println("Restarting PID: " + id);
                }
            }


        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
