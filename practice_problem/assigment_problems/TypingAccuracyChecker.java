package practice_problem.assigment_problems;

public class TypingAccuracyChecker {

    static void checkTypingAccuracy(String original, String typed) {
        if (original == null || typed == null) {
            System.out.println("Invalid input: null string");
            return;
        }
        if (original.length() != typed.length()) {
            System.out.println("Error: Strings must be of equal length");
            return;
        }

        int total = original.length();
        if (total == 0) {
            System.out.println("Matched: 0/0");
            System.out.println("Accuracy: 100.00%");
            System.out.println("No Mismatches");
            return;
        }

        int matched = 0;
        int firstMismatch = -1;

        for (int i = 0; i < total; i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                matched++;
            } else if (firstMismatch == -1) {
                firstMismatch = i;
            }
        }

        double accuracy = (matched * 100.0) / total;

        System.out.println("Matched: " + matched + "/" + total);
        System.out.printf("Accuracy: %.2f%%\n", accuracy);

        if (firstMismatch == -1) {
            System.out.println("No Mismatches");
        } else {
            System.out.println("First Mismatch at position " + (firstMismatch + 1)
                    + " ('" + original.charAt(firstMismatch) + "' vs '" + typed.charAt(firstMismatch) + "')");
        }
    }

    public static void main(String[] args) {
        System.out.println("Test 1:");
        checkTypingAccuracy("hello world", "hello worlt");
        System.out.println();
        System.out.println("Test 2:");
        checkTypingAccuracy("coding", "coding");
    }
}
