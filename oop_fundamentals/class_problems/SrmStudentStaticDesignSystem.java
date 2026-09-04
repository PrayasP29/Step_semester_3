package oop_fundamentals.class_problems;

public class SrmStudentStaticDesignSystem {

    // BROKEN design: all fields static -> shared across all objects
    static class BrokenSrmStudent {
        static String name;
        static String regNo;
        static int attendance;

        BrokenSrmStudent(String n, String r, int a) {
            name = n;
            regNo = r;
            attendance = a;
        }
    }

    // CORRECTED design
    static class SrmStudent {
        String name;
        String regNo;
        int attendance;

        static String university = "SRMIST";
        static int admissionCount = 0;

        SrmStudent(String name, int attendance) {
            this.name = name;
            this.attendance = attendance;
            admissionCount++;
            this.regNo = "RA231100301" + String.format("%03d", 10 + admissionCount);
            // RA231100301011 for first, 012 for second, etc.
        }

        void printIdCard() {
            System.out.println(name + " | " + regNo);
        }

        static void printTotalAdmissions() {
            System.out.println("Students admitted so far: " + admissionCount);
        }
    }

    public static void main(String[] args) {
        System.out.println("Broken version:");
        BrokenSrmStudent b1 = new BrokenSrmStudent("Ravi", "RA231100301011", 82);
        BrokenSrmStudent b2 = new BrokenSrmStudent("Meera", "RA231100301012", 75);
        // name is static: second overwrites first, both print Meera
        System.out.println(BrokenSrmStudent.name);
        System.out.println(BrokenSrmStudent.name);
        // why incorrect:
        // - static name: shared, not per student
        // - static regNo: shared, not per student
        // - static attendance: shared, not per student

        System.out.println("\nFixed version:");
        SrmStudent s1 = new SrmStudent("Ravi", 82);
        SrmStudent s2 = new SrmStudent("Meera", 74);
        s1.printIdCard();
        s2.printIdCard();
        SrmStudent.printTotalAdmissions();

        // instance vs static: university shared correctly as static, attendance per student as instance
    }
}
