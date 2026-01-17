import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConfigValidator {

    /*
     * Problem: Config Validator (The "Dirty Data" Challenge)
     *
     * Scenario:
     *   You are auditing a cluster. You have a file of machine configs.
     *
     * Input Format:
     *   hostname:cpu_cores:ram_gb (e.g., db-01:16:64)
     *
     * The Rules:
     *   1. Valid: A machine is valid if it has at least 4 CPU cores AND at least 8 GB of RAM
     *   2. Comments: Lines starting with # are comments and must be ignored
     *   3. Twist: Some lines are "Legacy" and use a different delimiter (hostname,cpu,ram)
     *      You must handle BOTH : and , delimiters
     *
     * Task:
     *   Print the hostname of every machine that FAILS the validation (i.e., too small)
     *
     * Key Skills:
     *   - startsWith("#") for filtering comments
     *   - Handling multiple delimiters (: and ,)
     *   - Parsing ints from specific positions
     *   - Filtering based on validation rules
     */
    public static void main(String[] args) {
        //open file via BufferedReader
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            //process line by line
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                processLine(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
            
       

        //inmplement helper method to check for validity

        //do validation analysis

    }

    static void processLine(String line) {

        //inmplement helper method to check for validity

        //rule 1: ignore empty lline
        if(line.isEmpty() || line.isBlank()) {
            System.out.println("empty line");
            return;
        }

        //rule2: ignore anything that starts with #
        if(line.startsWith("#")) {
            System.out.println("line is invalid");
            return;
        }

        //rule3: , & : are valid regexes
        String[] machineInfo = line.split("[,:]");

        //rule 4: print only if invalid
        //machineName: CPU cores: GB ram
        // Valid: A machine is valid if it has at least 4 CPU cores AND at least 8 GB of RAM

        int cpuCores = Integer.parseInt(machineInfo[1].trim());
        int ram = Integer.parseInt(machineInfo[2].trim());

        if(cpuCores < 4 || ram < 8) {
            System.out.println("invalid config machine name: " + machineInfo[0]);
            System.out.println(machineInfo[0] + " " + machineInfo[1] + " " + machineInfo[2]);
        }

        
    }
}
