import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;

public class Windows {

    /*
     * Problem: Merge Time Intervals (Maintenance Window)
     *
     * The Task:
     *   You are given a text file of server maintenance windows (start time, end time).
     *   Merge all overlapping windows to determine the total downtime.
     *
     * Input Example:
     *   09:00,10:30
     *   10:00,11:00
     *
     * Output Example:
     *   09:00,11:00
     *
     * Why it's crucial:
     *   Tests your ability to convert String timestamps into comparable objects
     *   (or integers) and apply logical sorting.
     *
     * Java Focus:
     *   - List.sort() for sorting intervals
     *   - Custom Comparator for comparing time intervals
     *   - class Interval for representing time windows
     */
    public static void main(String[] args) {
        // Read intervals from input
        ArrayList<Interval> downtimes = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Interval window = new Interval(line);
                downtimes.add(window);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Sort intervals by start time
        downtimes.sort(Comparator.comparing(slot -> slot.startTime));

        // Merge overlapping intervals and output
        System.out.println(getConsolidatedSlots(downtimes));
    }

    public static ArrayList<Interval> getConsolidatedSlots(ArrayList<Interval> slots) {
        ArrayList<Interval> consolidatedSlots = new ArrayList<>();
        consolidatedSlots.add(slots.getFirst());

        for (int i = 1; i < slots.size(); i++) {
            // Check if current interval overlaps with the last merged interval
            if (consolidatedSlots.getLast().endTime.isAfter(slots.get(i).startTime)) {
                // Overlapping - expand the merged interval
                Interval expanded = consolidatedSlots.removeLast();
                LocalTime maxEnd = max(expanded.endTime, slots.get(i).endTime);
                consolidatedSlots.add(new Interval(expanded.startTime, maxEnd));
            } else {
                // No overlap - add current interval as-is
                consolidatedSlots.add(slots.get(i));
            }
        }

        return consolidatedSlots;
    }

    public static LocalTime max(LocalTime time1, LocalTime time2) {
        return time1.isAfter(time2) ? time1 : time2;
    }

    static class Interval {
        LocalTime startTime;
        LocalTime endTime;

        Interval(String line) {
            String[] timeslot;
            try {
                timeslot = line.split(",");
                startTime = LocalTime.parse(timeslot[0]);
                endTime = LocalTime.parse(timeslot[1]);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        Interval(LocalTime start, LocalTime end) {
            startTime = start;
            endTime = end;
        }

        public String toString() {
            return "Timeslot start: " + startTime + " / Timeslot end: " + endTime;
        }
    }
}
