package string_processing.class_problems;

public class CSVStudentRecordParser {

    static void parseRecord(String csv) {
        String[] parts = csv.split(",");
        if (parts.length != 3) {
            System.out.println("Invalid Record");
            return;
        }
        String name = parts[0].trim();
        String roll = parts[1].trim();
        String dept = parts[2].trim();
        System.out.println("Name: " + name + " | Roll No: " + roll + " | Dept: " + dept);
    }

    public static void main(String[] args) {
        parseRecord("Ananya Verma,RA2211003010123,CSE");
        parseRecord("Ananya Verma,CSE");
        parseRecord("John Doe,123,EEE");
    }
}

