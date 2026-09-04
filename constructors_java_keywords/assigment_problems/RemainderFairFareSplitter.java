package constructors_java_keywords.assigment_problems;

public class RemainderFairFareSplitter {

    static class FareSplitter {
        String tripId;
        double totalFare;
        int passengerCount;

        public FareSplitter(String tripId, double totalFare, int passengerCount) {
            if (tripId == null || tripId.trim().isEmpty()) {
                throw new IllegalArgumentException("tripId required");
            }
            if (totalFare < 0) {
                throw new IllegalArgumentException("negative fare");
            }
            if (passengerCount <= 0) {
                throw new IllegalArgumentException("passengerCount must be positive");
            }
            this.tripId = tripId;
            this.totalFare = totalFare;
            this.passengerCount = passengerCount;
        }

        public FareSplitter(String tripId, double totalFare) {
            this(tripId, totalFare, 2);
        }

        public FareSplitter(String tripId) {
            this(tripId, 0.0, 2);
        }

        double[] fareBreakdown() {
            long totalCents = Math.round(totalFare * 100);
            long base = totalCents / passengerCount;
            long remainder = totalCents % passengerCount;
            double[] shares = new double[passengerCount];
            for (int i = 0; i < passengerCount; i++) {
                long cents = base;
                if (i == passengerCount - 1) {
                    cents += remainder;
                }
                shares[i] = cents / 100.0;
            }
            return shares;
        }

        boolean isConfirmationOverdue(int confirmed, int expected) {
            if (expected <= 0) return false;
            if (confirmed < 0) confirmed = 0;
            return confirmed < expected;
        }
    }

    public static void main(String[] args) {
        FareSplitter f1 = new FareSplitter("TRIP001", 100000, 3);
        double[] b1 = f1.fareBreakdown();
        System.out.println(java.util.Arrays.toString(b1));
        double sum = 0; for (double d : b1) sum += d; System.out.println("Sum: " + sum);

        FareSplitter f2 = new FareSplitter("TRIP002", 100, 2);
        System.out.println(java.util.Arrays.toString(f2.fareBreakdown()));

        FareSplitter f3 = new FareSplitter("TRIP003");
        System.out.println(java.util.Arrays.toString(f3.fareBreakdown()));

        // edge: negative fare
        try { new FareSplitter("TRIP004", -10, 2); } catch (Exception e) { System.out.println("negative fare rejected"); }
        // zero passenger
        try { new FareSplitter("TRIP005", 100, 0); } catch (Exception e) { System.out.println("zero passenger rejected"); }
        // overdue
        System.out.println("Overdue? " + f1.isConfirmationOverdue(1, 3));
        System.out.println("Overdue? " + f1.isConfirmationOverdue(3, 3));
    }
}
