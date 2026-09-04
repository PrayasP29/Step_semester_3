package practice_problem.assigment_problems;

public class TrafficSignalStreakAnalyzer {

    static void findLongestStreak(String signalLog) {
        if (signalLog == null || signalLog.length() == 0) {
            System.out.println("No Signal Data");
            return;
        }

        char longestChar = signalLog.charAt(0);
        int longestLength = 1;

        char currentChar = signalLog.charAt(0);
        int currentLength = 1;

        for (int i = 1; i < signalLog.length(); i++) {
            if (signalLog.charAt(i) == currentChar) {
                currentLength++;
            } else {
                if (currentLength > longestLength) {
                    longestLength = currentLength;
                    longestChar = currentChar;
                }
                currentChar = signalLog.charAt(i);
                currentLength = 1;
            }
        }

        if (currentLength > longestLength) {
            longestLength = currentLength;
            longestChar = currentChar;
        }

        System.out.println("Longest Streak: '" + longestChar + "' repeated " + longestLength + " times");
    }

    public static void main(String[] args) {
        System.out.println("Test 1:");
        findLongestStreak("RRGGGYRR");
        System.out.println();
        System.out.println("Test 2:");
        findLongestStreak("RRRRYYGG");
        System.out.println();
        System.out.println("Test 3 (empty):");
        findLongestStreak("");
    }
}
