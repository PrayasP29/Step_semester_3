package oop_fundamentals.assigment_problems;

public class EmployeePayrollSystem {

    static class Employee {
        private String empId;
        private String empName;
        private double salary;

        Employee(String empId, String empName, double salary) {
            this.empId = empId;
            this.empName = empName;
            this.salary = salary;
        }

        double getSalary() {
            return salary;
        }

        String getName() {
            return empName;
        }
    }

    static class ManagerEmployee extends Employee {
        private double teamBonus;

        ManagerEmployee(String empId, String empName, double salary, double teamBonus) {
            super(empId, empName, salary);
            this.teamBonus = teamBonus;
        }

        double effectiveSalary() {
            return getSalary() + teamBonus;
        }
    }

    static class InternEmployee extends Employee {
        private double stipendCap;

        InternEmployee(String empId, String empName, double salary, double stipendCap) {
            super(empId, empName, salary);
            this.stipendCap = stipendCap;
        }

        double effectiveSalary() {
            double s = getSalary();
            return s < stipendCap ? s : stipendCap;
        }
    }

    public static void main(String[] args) {
        Employee e1 = new Employee("E001", "Plain", 40000);
        System.out.println("Plain employee pay: Rs " + e1.getSalary());

        ManagerEmployee m1 = new ManagerEmployee("E002", "Manager", 50000, 28000);
        System.out.println("Manager effective pay: Rs " + m1.effectiveSalary());

        InternEmployee i1 = new InternEmployee("E003", "Intern", 40000, 10000);
        System.out.println("Intern effective pay: Rs " + i1.effectiveSalary());

        // instanceof
        Employee[] staff = {e1, m1, i1};
        for (int i = 0; i < staff.length; i++) {
            if (staff[i] instanceof ManagerEmployee) {
                System.out.println("Found manager");
            } else if (staff[i] instanceof InternEmployee) {
                System.out.println("Found intern");
            } else {
                System.out.println("Found plain employee");
            }
        }

        // edge: intern cap higher than salary
        InternEmployee i2 = new InternEmployee("E004", "Intern2", 8000, 10000);
        System.out.println("Intern2 effective: Rs " + i2.effectiveSalary());
    }
}
