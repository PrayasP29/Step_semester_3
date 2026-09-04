package oop_fundamentals.class_problems;

public class SrmFeeHostelManagementSystem {

    static class FeeAccount {
        private String regNo;
        private double totalFee;
        private double amountPaid;

        FeeAccount(String regNo, double totalFee, double amountPaid) {
            this.regNo = regNo;
            this.totalFee = totalFee;
            this.amountPaid = amountPaid;
        }

        void pay(double amount) {
            if (amount <= 0) {
                System.out.println("Rejected: amount must be positive");
                return;
            }
            amountPaid += amount;
        }

        double getDue() {
            return totalFee - amountPaid;
        }
    }

    static class HostelFeeAccount extends FeeAccount {
        HostelFeeAccount(String regNo, double totalFee, double amountPaid) {
            super(regNo, totalFee, amountPaid);
        }
    }

    static class HostelRoom {
        String roomNo;
        int beds;
        int occupied;

        HostelRoom(String roomNo, int beds, int occupied) {
            this.roomNo = roomNo;
            this.beds = beds;
            this.occupied = occupied;
        }

        void allot(String name) {
            if (occupied < beds) {
                occupied++;
            }
        }
    }

    static HostelRoom findAvailableRoom(HostelRoom[] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i].occupied < rooms[i].beds) return rooms[i];
        }
        return null;
    }

    static void safeAllot(HostelRoom[] rooms, String name, SrmStudent s) {
        HostelRoom r = findAvailableRoom(rooms);
        if (r != null) {
            r.allot(name);
            s.room = r;
        }
    }

    static class SrmStudent {
        String name;
        String regNo;
        HostelFeeAccount feeAccount;
        HostelRoom room;
        static int totalStudents = 0;

        SrmStudent(String name, String regNo, double totalFee) {
            this.name = name;
            this.regNo = regNo;
            this.feeAccount = new HostelFeeAccount(regNo, totalFee, 0);
            this.room = null;
            totalStudents++;
        }

        String fullStatus() {
            String roomStr = (room == null) ? "unallotted" : room.roomNo;
            return name + " | Due: Rs " + feeAccount.getDue() + " | Room: " + roomStr;
        }
    }

    public static void main(String[] args) {
        HostelRoom[] rooms = {
            new HostelRoom("C-214", 1, 0),
            new HostelRoom("C-507", 1, 0)
        };

        SrmStudent s1 = new SrmStudent("Ravi", "RA001", 200000);
        SrmStudent s2 = new SrmStudent("Anitha", "RA002", 200000);
        SrmStudent s3 = new SrmStudent("Karthik", "RA003", 200000);

        safeAllot(rooms, s1.name, s1);
        safeAllot(rooms, s2.name, s2);
        // s3 left unallotted

        s1.feeAccount.pay(60000);
        s2.feeAccount.pay(20000);
        s3.feeAccount.pay(-500);

        System.out.println(s1.fullStatus());
        System.out.println(s2.fullStatus());
        System.out.println(s3.fullStatus());
        System.out.println("Total students: " + SrmStudent.totalStudents);
    }
}
