package constructors_java_keywords.assigment_problems;

public final class TieredBoardingPenaltyCalculator {

    static final class BoardingPenaltyCalculator {
        private final double minimumPenaltyPercent;

        public BoardingPenaltyCalculator(double minimumPenaltyPercent) {
            if (minimumPenaltyPercent < 0) {
                throw new IllegalArgumentException("minimumPenaltyPercent negative");
            }
            this.minimumPenaltyPercent = minimumPenaltyPercent;
        }

        public final double calculatePenalty(double ticketFare, int minutesLate) {
            if (ticketFare < 0) throw new IllegalArgumentException("negative ticketFare");
            if (minutesLate < 0) throw new IllegalArgumentException("negative minutesLate");
            if (minutesLate == 0) return 0.0;

            final double fare = ticketFare;
            final int late = minutesLate;

            final double tier1 = Math.min(late, 5) * 0.005 * fare;
            final double tier2 = Math.max(0, Math.min(late, 15) - 5) * 0.01 * fare;
            final double tier3 = Math.max(0, late - 15) * 0.02 * fare;

            final double tiered = tier1 + tier2 + tier3;
            final double floor = minimumPenaltyPercent / 100.0 * fare;
            return Math.max(tiered, floor);
        }
    }

    public static void main(String[] args) {
        BoardingPenaltyCalculator calc = new BoardingPenaltyCalculator(1.0);
        System.out.println("0 min: Rs " + calc.calculatePenalty(1000, 0));
        System.out.println("1 min: Rs " + calc.calculatePenalty(1000, 1));
        System.out.println("5 min: Rs " + calc.calculatePenalty(1000, 5));
        System.out.println("6 min: Rs " + calc.calculatePenalty(1000, 6));
        System.out.println("15 min: Rs " + calc.calculatePenalty(1000, 15));
        System.out.println("16 min: Rs " + calc.calculatePenalty(1000, 16));

        // verify bracket boundaries
        // 5 min: 5*0.5%*1000=25, floor 10 => 25
        // 6 min: 25 +1*1%*1000=35 => 35
        // 15 min: 25+10*10=125 =>125
        // 16 min: 125+20=145 =>145 matches sample

        // negative checks
        try { calc.calculatePenalty(-100, 5); } catch (Exception e) { System.out.println("negative fare rejected"); }
        try { calc.calculatePenalty(1000, -1); } catch (Exception e) { System.out.println("negative late rejected"); }

        // floor test: tiered below floor
        BoardingPenaltyCalculator calc2 = new BoardingPenaltyCalculator(5.0);
        System.out.println("1 min with 5% floor: Rs " + calc2.calculatePenalty(1000, 1));
    }
}
