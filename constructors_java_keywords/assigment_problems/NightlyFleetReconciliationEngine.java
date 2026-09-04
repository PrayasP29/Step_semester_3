package constructors_java_keywords.assigment_problems;

public class NightlyFleetReconciliationEngine {

    static class BusTicketAccount {
        String bookingId;
        double ticketFare;
        static String fleetName;

        static {
            fleetName = "SRM Fleet";
            System.out.println("BusTicketAccount class loaded: " + fleetName);
        }

        public BusTicketAccount(String bookingId, double ticketFare) {
            if (bookingId == null || bookingId.trim().isEmpty()) {
                throw new IllegalArgumentException("bookingId required");
            }
            if (ticketFare < 0) throw new IllegalArgumentException("negative fare");
            this.bookingId = bookingId;
            this.ticketFare = ticketFare;
        }

        public BusTicketAccount(String bookingId) {
            this(bookingId, 0.0);
        }

        public final double calculatePenalty(int minutesLate) {
            if (minutesLate < 0) throw new IllegalArgumentException("negative minutesLate");
            if (minutesLate == 0) return 0.0;
            // Reuse Problem 4 tiered calculation with 1% floor — explicitly stated reuse
            double tier1 = Math.min(minutesLate, 5) * 0.005 * ticketFare;
            double tier2 = Math.max(0, Math.min(minutesLate, 15) - 5) * 0.01 * ticketFare;
            double tier3 = Math.max(0, minutesLate - 15) * 0.02 * ticketFare;
            double tiered = tier1 + tier2 + tier3;
            double floor = 0.01 * ticketFare;
            return Math.max(tiered, floor);
        }
    }

    static class SleeperBusTicketAccount extends BusTicketAccount {
        SleeperBusTicketAccount(String bookingId, double ticketFare) {
            super(bookingId, ticketFare);
        }
        SleeperBusTicketAccount(String bookingId) {
            super(bookingId);
        }
    }

    // Processor state
    int processed = 0;
    int nullSkipped = 0;
    int sleeperCount = 0;
    int regularCount = 0;
    double grandTotal = 0.0;

    void processAccount(BusTicketAccount account, double amount, int minutesLate) {
        if (account == null) {
            nullSkipped++;
            return;
        }
        // safely handle null, never throw uncaught
        try {
            double penalty = account.calculatePenalty(minutesLate);
            grandTotal += penalty;
            processed++;
            if (account instanceof SleeperBusTicketAccount) {
                sleeperCount++;
            } else {
                regularCount++;
            }
            // amount could be used for reconciliation, but penalty is main
        } catch (Exception e) {
            System.out.println("Error processing " + account.bookingId + ": " + e.getMessage());
        }
    }

    static void processBatch(BusTicketAccount[] accounts, double[] amounts, int[] minutesLateArray) {
        if (accounts == null || amounts == null || minutesLateArray == null) {
            System.out.println("Invalid input arrays");
            return;
        }
        // Defensive decision: process only safe common range
        // If arrays have different lengths, using shortest prevents IndexOutOfBounds
        // and ensures only genuinely parallel entries are processed
        int safe = Math.min(accounts.length, Math.min(amounts.length, minutesLateArray.length));
        NightlyFleetReconciliationEngine engine = new NightlyFleetReconciliationEngine();
        for (int i = 0; i < safe; i++) {
            engine.processAccount(accounts[i], amounts[i], minutesLateArray[i]);
        }
        System.out.println("Processed: " + engine.processed + " | Null skipped: " + engine.nullSkipped + " | Sleeper: " + engine.sleeperCount + " | Regular: " + engine.regularCount + " | Grand total penalties: Rs " + engine.grandTotal);
    }

    public static void main(String[] args) {
        BusTicketAccount[] accounts = {
            new SleeperBusTicketAccount("BK001", 2000),
            null,
            new BusTicketAccount("BK002", 1200)
        };
        double[] amounts = {1200, 900, 700};
        int[] minutesLateArray = {10, 5, 0};
        processBatch(accounts, amounts, minutesLateArray);

        // mismatched lengths
        BusTicketAccount[] a2 = { new BusTicketAccount("BK003", 1000), new BusTicketAccount("BK004", 1000) };
        double[] amt2 = {500};
        int[] late2 = {5, 6, 7};
        processBatch(a2, amt2, late2);

        // regular vs sleeper
        BusTicketAccount regular = new BusTicketAccount("BK005", 1000);
        SleeperBusTicketAccount sleeper = new SleeperBusTicketAccount("BK006", 1000);
        System.out.println("Regular penalty 10 min: " + regular.calculatePenalty(10));
        System.out.println("Sleeper penalty 10 min: " + sleeper.calculatePenalty(10));
    }
}
