package string_processing.class_problems;

public class BankTransactionReferenceGeneratorValidator {

    static String normalizeReference(String raw) {
        if (raw == null) {
            return "";
        }
        String t = raw.trim();
        if (t.length() < 3) {
            return t.toUpperCase();
        }
        String first3 = t.substring(0, 3).toUpperCase();
        String rest = t.substring(3);
        return first3 + rest;
    }

    static String validateAndFormat(String reference) {
        if (reference == null) {
            return "Invalid: reference must be 14 characters";
        }
        if (reference.length() != 14) {
            return "Invalid: reference must be 14 characters";
        }
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(reference.charAt(i))) {
                return "Invalid: bank code must be 3 letters";
            }
        }
        for (int i = 3; i < 14; i++) {
            if (!Character.isDigit(reference.charAt(i))) {
                return "Invalid: body must be 11 digits";
            }
        }
        String bank = reference.substring(0, 3);
        String datePart = reference.substring(3, 9);
        String seq = reference.substring(9, 14);
        String dd = datePart.substring(0, 2);
        String mm = datePart.substring(2, 4);
        String yy = datePart.substring(4, 6);
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(bank);
        sb.append("] DATE: ");
        sb.append(dd);
        sb.append("/");
        sb.append(mm);
        sb.append("/");
        sb.append(yy);
        sb.append(" | SEQ: ");
        sb.append(seq);
        return sb.toString();
    }

    public static void main(String[] args) {
        String r1 = normalizeReference(" hdf03022600042 ");
        System.out.println(validateAndFormat(r1));
        String r2 = normalizeReference("12F03022600042");
        System.out.println(validateAndFormat(r2));
        String r3 = normalizeReference("HDF03022600042");
        System.out.println(validateAndFormat(r3));
        String r4 = normalizeReference("HDF03022A00042");
        System.out.println(validateAndFormat(r4));
    }
}

