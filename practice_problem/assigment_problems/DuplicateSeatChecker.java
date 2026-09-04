package practice_problem.assigment_problems;

public class DuplicateSeatChecker {

    static void checkDuplicateSeats(int[] seatNumbers) {

        boolean duplicateFound = false;

        for (int i = 0; i < seatNumbers.length; i++) {

            for (int j = i + 1; j < seatNumbers.length; j++) {

                if (seatNumbers[i] == seatNumbers[j]) {

                    // Check if this duplicate was already printed
                    boolean alreadyPrinted = false;

                    for (int k = 0; k < i; k++) {
                        if (seatNumbers[k] == seatNumbers[i]) {
                            alreadyPrinted = true;
                            break;
                        }
                    }

                    if (!alreadyPrinted) {
                        System.out.println(
                                "Duplicate Seat Number Found: " + seatNumbers[i]
                        );
                        duplicateFound = true;
                        // prevent printing same value multiple times for same i
                        break;
                    }
                }
            }
        }

        if (!duplicateFound) {
            System.out.println("No Duplicate Seats Found");
        }
    }

    public static void main(String[] args) {

        int[] seatNumbers = {101, 102, 103, 102, 105};

        checkDuplicateSeats(seatNumbers);
    }
}