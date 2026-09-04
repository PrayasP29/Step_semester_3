package practice_problem.assigment_problems;

public class MovieReviewWordLengthProfiler {

    static void classifyWordLengths(String review) {
        int shortCount = 0;
        int mediumCount = 0;
        int longCount = 0;

        if (review == null || review.trim().isEmpty()) {
            System.out.println("Short: 0");
            System.out.println("Medium: 0");
            System.out.println("Long: 0");
            return;
        }

        String[] words = review.trim().split("\\s+");

        for (int i = 0; i < words.length; i++) {
            int len = words[i].length();
            if (len >= 1 && len <= 4) {
                shortCount++;
            } else if (len >= 5 && len <= 8) {
                mediumCount++;
            } else if (len >= 9) {
                longCount++;
            }
        }

        System.out.println("Short: " + shortCount);
        System.out.println("Medium: " + mediumCount);
        System.out.println("Long: " + longCount);
    }

    public static void main(String[] args) {
        classifyWordLengths("This movie was absolutely fantastic and thrilling");
    }
}
