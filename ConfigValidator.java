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

        //process line by line

        //inmplement helper method to check for validity

        //do validation analysis

    }
}
