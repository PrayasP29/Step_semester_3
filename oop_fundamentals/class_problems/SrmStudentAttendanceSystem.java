package oop_fundamentals.class_problems;

public class SrmStudentAttendanceSystem {

    static class SrmStudent {
        String name;
        String regNo;
        int attendance;

        SrmStudent(String name, String regNo, int attendance) {
            this.name = name;
            this.regNo = regNo;
            this.attendance = attendance;
        }

        boolean isEligible() {
            return attendance >= 75;
        }

        void addAttendanceUpdate(int newAttendance) {
            this.attendance = newAttendance;
        }
    }

    // classAverage is static because it computes across all students, not one instance;
    // isEligible is instance because it checks this student's attendance
    static double classAverage(SrmStudent[] students) {
        int sum = 0;
        for (int i = 0; i < students.length; i++) {
            sum += students[i].attendance;
        }
        return (double) sum / students.length;
    }

    public static void main(String[] args) {
        SrmStudent s1 = new SrmStudent("Ravi", "RA231100301001", 82);
        SrmStudent s2 = new SrmStudent("Anitha", "RA231100301002", 68);
        SrmStudent s3 = new SrmStudent("Karthik", "RA231100301003", 91);
        SrmStudent s4 = new SrmStudent("Meera", "RA231100301004", 74);
        SrmStudent s5 = new SrmStudent("Suresh", "RA231100301005", 60);

        SrmStudent[] students = {s1, s2, s3, s4, s5};

        for (int i = 0; i < students.length; i++) {
            String status = students[i].isEligible() ? "Eligible" : "Detained";
            System.out.println(students[i].name + " - " + students[i].attendance + "% - " + status);
        }

        double avg = classAverage(students);
        System.out.println("Class average: " + avg + "%");

        // edge case: exactly 75
        SrmStudent edge = new SrmStudent("Edge", "RA231100301006", 75);
        System.out.println(edge.name + " eligible? " + edge.isEligible());

        // attendance update
        s2.addAttendanceUpdate(80);
        System.out.println("After update: " + s2.name + " - " + s2.attendance + "% - " + (s2.isEligible() ? "Eligible" : "Detained"));
    }
}
