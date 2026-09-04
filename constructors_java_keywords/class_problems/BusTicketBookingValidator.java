package constructors_java_keywords.class_problems;

import java.util.HashSet;

public class BusTicketBookingValidator {

    static class BusTicket {
        String passengerName;
        String destination;
        boolean checkedIn;

        // No usable no-argument constructor - only parameterized
        public BusTicket(String passengerName, String destination) {
            // Validation decisions:
            // - passengerName: not null, trimmed not empty, only letters and spaces, at least one letter, no digits/special chars
            // - destination: not null, trimmed not empty, only letters and spaces
            // - both fields fail at construction time via IllegalArgumentException
            if (!isValidName(passengerName)) {
                throw new IllegalArgumentException("Invalid passengerName: " + passengerName);
            }
            if (!isValidDestination(destination)) {
                throw new IllegalArgumentException("Invalid destination: " + destination);
            }
            this.passengerName = passengerName.trim();
            this.destination = destination.trim();
            this.checkedIn = false;
        }

        static boolean isValidName(String s) {
            if (s == null) return false;
            String t = s.trim();
            if (t.isEmpty()) return false;
            // must contain only letters and spaces, at least one letter
            boolean hasLetter = false;
            for (int i = 0; i < t.length(); i++) {
                char c = t.charAt(i);
                if (c == ' ') continue;
                if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
                    hasLetter = true;
                } else {
                    return false;
                }
            }
            return hasLetter;
        }

        static boolean isValidDestination(String s) {
            if (s == null) return false;
            String t = s.trim();
            if (t.isEmpty()) return false;
            boolean hasLetter = false;
            for (int i = 0; i < t.length(); i++) {
                char c = t.charAt(i);
                if (c == ' ') continue;
                if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
                    hasLetter = true;
                } else {
                    return false;
                }
            }
            return hasLetter;
        }

        void markCheckedIn() {
            if (checkedIn) {
                System.out.println("Already checked in: " + passengerName + " to " + destination);
                return;
            }
            checkedIn = true;
            System.out.println("Checked in: " + passengerName + " to " + destination);
        }
    }

    static void processBatch(String[][] rawBookings) {
        if (rawBookings == null) {
            System.out.println("Valid: 0 | Rejected: 0 | Duplicates skipped: 0");
            return;
        }
        int limit = Math.min(rawBookings.length, 1000);
        int valid = 0;
        int rejected = 0;
        int duplicates = 0;
        HashSet<String> seen = new HashSet<>();

        for (int i = 0; i < limit; i++) {
            String[] entry = rawBookings[i];
            if (entry == null || entry.length != 2) {
                rejected++;
                continue;
            }
            String name = entry[0];
            String dest = entry[1];
            String key = (name == null ? "null" : name.trim().toLowerCase()) + "|" + (dest == null ? "null" : dest.trim().toLowerCase());
            // try construction to validate
            try {
                BusTicket t = new BusTicket(name, dest);
                if (seen.contains(key)) {
                    duplicates++;
                } else {
                    seen.add(key);
                    valid++;
                }
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }
        System.out.println("Valid: " + valid + " | Rejected: " + rejected + " | Duplicates skipped: " + duplicates);
    }

    public static void main(String[] args) {
        String[][] sample = {
            {"Divya", "Chennai"},
            {"", "Bangalore"},
            {"Ravi123", "Pune"},
            {"Divya", "Chennai"},
            {" ", " "}
        };
        processBatch(sample);

        // edge cases
        System.out.println("--- edge tests ---");
        // null name
        try { new BusTicket(null, "Chennai"); } catch (Exception e) { System.out.println("null name rejected"); }
        // whitespace only
        try { new BusTicket("   ", "Chennai"); } catch (Exception e) { System.out.println("whitespace name rejected"); }
        // invalid name with digits
        try { new BusTicket("Ravi123", "Pune"); } catch (Exception e) { System.out.println("digit name rejected"); }
        // duplicate check
        String[][] dup = {{"Amit","Delhi"},{"Amit","Delhi"}};
        processBatch(dup);
        // second check-in
        BusTicket t = new BusTicket("Amit", "Delhi");
        t.markCheckedIn();
        t.markCheckedIn();
    }
}

