package oop_fundamentals.assigment_problems;

public class BookIssueFineSystem {

    static class BookIssue {
        String title;
        String borrowerName;
        int daysOverdue;

        BookIssue(String title, String borrowerName, int daysOverdue) {
            this.title = title;
            this.borrowerName = borrowerName;
            this.daysOverdue = daysOverdue;
        }

        double fineAmount() {
            if (daysOverdue > 0) return daysOverdue * 5;
            return 0;
        }

        boolean isSeverelyOverdue() {
            return daysOverdue > 14;
        }
    }

    // totalFineCollected is static because it aggregates over array, not single issue
    static double totalFineCollected(BookIssue[] issues) {
        double sum = 0;
        for (int i = 0; i < issues.length; i++) {
            sum += issues[i].fineAmount();
        }
        return sum;
    }

    public static void main(String[] args) {
        BookIssue b1 = new BookIssue("Clean Code", "A", 18);
        BookIssue b2 = new BookIssue("Effective Java", "B", 5);
        BookIssue b3 = new BookIssue("Refactoring", "C", 0);
        BookIssue b4 = new BookIssue("DSA Handbook", "D", 21);
        BookIssue b5 = new BookIssue("Design Patterns", "E", 9);

        BookIssue[] issues = {b1, b2, b3, b4, b5};

        for (int i = 0; i < issues.length; i++) {
            String status = issues[i].isSeverelyOverdue() ? "Severely overdue" : "OK";
            System.out.println(issues[i].title + " - " + issues[i].daysOverdue + " days - " + status);
        }

        System.out.println("Total fine collected: Rs " + totalFineCollected(issues));

        // edge: zero overdue fine 0
        System.out.println("Zero fine edge: " + b3.fineAmount());
    }
}
