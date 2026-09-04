package oop_fundamentals.class_problems;

public class FeeAccountInheritanceSystem {

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

        void payInTwoInstallments(double amount) {
            double half = amount / 2;
            pay(half);
            pay(half);
        }
    }

    static class ScholarshipFeeAccount extends FeeAccount {
        private double scholarshipPercent;

        ScholarshipFeeAccount(String regNo, double totalFee, double amountPaid, double scholarshipPercent) {
            super(regNo, totalFee, amountPaid);
            this.scholarshipPercent = scholarshipPercent;
        }

        double effectiveDue() {
            return getDue() * (1 - scholarshipPercent / 100);
        }
    }

    public static void main(String[] args) {
        FeeAccount plain = new FeeAccount("RA001", 200000, 0);
        plain.pay(200000);
        System.out.println("Plain account due: Rs " + plain.getDue());

        // rejected zero/negative
        plain.pay(0);
        plain.pay(-100);

        HostelFeeAccount hostel = new HostelFeeAccount("RA002", 200000, 0);
        hostel.pay(60000);
        System.out.println("Hostel account due: Rs " + hostel.getDue());

        // via two installments
        HostelFeeAccount hostel2 = new HostelFeeAccount("RA003", 200000, 0);
        hostel2.payInTwoInstallments(60000);
        System.out.println("Hostel (installments) due: Rs " + hostel2.getDue());

        ScholarshipFeeAccount scholar = new ScholarshipFeeAccount("RA004", 200000, 20000, 20);
        System.out.println("Scholarship account effective due: Rs " + scholar.effectiveDue());

        // instanceof
        FeeAccount[] accounts = {plain, hostel, scholar};
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] instanceof ScholarshipFeeAccount) {
                ScholarshipFeeAccount s = (ScholarshipFeeAccount) accounts[i];
                System.out.println("Found scholarship: effective due Rs " + s.effectiveDue());
            } else if (accounts[i] instanceof HostelFeeAccount) {
                System.out.println("Found hostel account");
            } else {
                System.out.println("Found plain account");
            }
        }
    }
}
