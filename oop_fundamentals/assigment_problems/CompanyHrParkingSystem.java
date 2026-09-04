package oop_fundamentals.assigment_problems;

public class CompanyHrParkingSystem {

    static class Employee {
        private String empId;
        private String empName;
        private double salary;

        Employee(String empId, String empName, double salary) {
            this.empId = empId;
            this.empName = empName;
            this.salary = salary;
        }

        double getSalary() { return salary; }
        String getName() { return empName; }
    }

    static class ManagerEmployee extends Employee {
        private double teamBonus;
        ManagerEmployee(String empId, String empName, double salary, double teamBonus) {
            super(empId, empName, salary);
            this.teamBonus = teamBonus;
        }
        double effectiveSalary() { return getSalary() + teamBonus; }
    }

    static class InternEmployee extends Employee {
        private double stipendCap;
        InternEmployee(String empId, String empName, double salary, double stipendCap) {
            super(empId, empName, salary);
            this.stipendCap = stipendCap;
        }
        double effectiveSalary() { return Math.min(getSalary(), stipendCap); }
    }

    static class ParkingSlot {
        String slotNo;
        int capacity;
        int occupied;
        ParkingSlot(String slotNo, int capacity, int occupied) {
            this.slotNo = slotNo;
            this.capacity = capacity;
            this.occupied = occupied;
        }
        void allot(String v) { if (occupied < capacity) occupied++; }
    }

    static ParkingSlot findAvailableSlot(ParkingSlot[] slots) {
        for (int i = 0; i < slots.length; i++) if (slots[i].occupied < slots[i].capacity) return slots[i];
        return null;
    }

    static class CompanyEmployeeRecord {
        String name;
        String empId;
        Employee employee;
        ParkingSlot slot;
        static int totalRecords = 0;

        CompanyEmployeeRecord(String name, String empId, Employee emp) {
            this.name = name;
            this.empId = empId;
            this.employee = emp;
            this.slot = null;
            totalRecords++;
        }

        String fullProfile() {
            double pay;
            if (employee instanceof ManagerEmployee) pay = ((ManagerEmployee) employee).effectiveSalary();
            else if (employee instanceof InternEmployee) pay = ((InternEmployee) employee).effectiveSalary();
            else pay = employee.getSalary();
            String slotStr = (slot == null) ? "no parking assigned" : slot.slotNo;
            return name + " | Pay: Rs " + pay + " | Slot: " + slotStr;
        }
    }

    static void safeAllot(ParkingSlot[] slots, CompanyEmployeeRecord rec) {
        ParkingSlot s = findAvailableSlot(slots);
        if (s != null) {
            s.allot(rec.name);
            rec.slot = s;
        }
    }

    public static void main(String[] args) {
        ParkingSlot[] slots = {
            new ParkingSlot("A1", 1, 0),
            new ParkingSlot("A2", 1, 0)
        };

        CompanyEmployeeRecord r1 = new CompanyEmployeeRecord("Divya", "E001", new ManagerEmployee("E001", "Divya", 50000, 28000));
        CompanyEmployeeRecord r2 = new CompanyEmployeeRecord("Karan", "E002", new Employee("E002", "Karan", 40000));
        CompanyEmployeeRecord r3 = new CompanyEmployeeRecord("Meera", "E003", new InternEmployee("E003", "Meera", 40000, 10000));

        safeAllot(slots, r1);
        safeAllot(slots, r2);
        // r3 left unallotted

        System.out.println(r1.fullProfile());
        System.out.println(r2.fullProfile());
        System.out.println(r3.fullProfile());
        System.out.println("Total records: " + CompanyEmployeeRecord.totalRecords);
    }
}
