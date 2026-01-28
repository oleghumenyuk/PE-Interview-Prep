public class ReplaceSensitiveChars {

    /*
     * Given a log file, replace all sensitive patterns
     * (like Credit Card numbers or API keys) with "XXXX".
     *
     * Java Focus: Regular Expressions (Pattern and Matcher).
     * 
     * 
     * Splitting on whitespace works for most of the log entries, since the sensitive values are standalone tokens.
Checking for - as a sensitive pattern is too broad — it would match things like auth-service, timestamp fields, and log levels. You'd want to be more specific, like checking if a word matches a credit card pattern (groups of digits separated by dashes, or 16 consecutive digits).
Prefix matching (sk_live, api_key, bearer_token) is straightforward and works well here.
Since this is a Pattern/Matcher problem, you might want to use regex for the credit card detection (e.g., \d{4}-\d{4}-\d{4}-\d{4} or \d{16}) rather than just checking for -. That way you avoid false positives.
     */
    public static void main(String[] args) {

    }
}
