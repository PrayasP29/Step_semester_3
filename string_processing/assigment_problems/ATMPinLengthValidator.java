package string_processing.assigment_problems;

public class ATMPinLengthValidator {

    static void validatePin(String pin) {
        if (pin == null) {
            System.out.println("Invalid PIN — must be exactly 4 digits.");
            return;
        }
        int len = pin.length();
        if (len != 4) {
            System.out.println("Invalid PIN — must be exactly 4 digits.");
        } else {
            System.out.println("PIN length OK.");
        }
    }

    public static void main(String[] args) {
        validatePin("482");
        validatePin("4820");
        validatePin("1234");
        validatePin("");
    }
}

