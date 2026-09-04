package string_processing.class_problems;

public class MaskedPhoneNumberFormatter {

    static void formatPhone(String phone) {
        if (phone == null || phone.length() != 10) {
            System.out.println("Invalid phone number");
            return;
        }
        for (int i = 0; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) {
                System.out.println("Invalid phone number");
                return;
            }
        }
        String last4 = phone.substring(6);
        StringBuilder sb = new StringBuilder();
        sb.append("XXXXXX");
        sb.append("-");
        sb.append(last4);
        // alternative using insert: sb.insert(6, "-");
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        formatPhone("9876543210");
        formatPhone("98765");
        formatPhone("987654321a");
    }
}

