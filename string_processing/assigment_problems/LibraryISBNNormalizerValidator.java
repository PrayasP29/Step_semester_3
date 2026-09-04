package string_processing.assigment_problems;

public class LibraryISBNNormalizerValidator {

    static String normalizeCode(String raw) {
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

    static String validateAndFormat(String code) {
        if (code == null) {
            return "Invalid: code must be 13 characters";
        }
        if (code.length() != 13) {
            return "Invalid: code must be 13 characters";
        }
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(code.charAt(i))) {
                return "Invalid: publisher code must be 3 letters";
            }
        }
        for (int i = 3; i < 13; i++) {
            if (!Character.isDigit(code.charAt(i))) {
                return "Invalid: body must be 10 digits";
            }
        }
        String pub = code.substring(0, 3);
        String year = code.substring(3, 7);
        String catalog = code.substring(7, 13);
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(pub);
        sb.append("] YEAR: ");
        sb.append(year);
        sb.append(" | CATALOG: ");
        sb.append(catalog);
        return sb.toString();
    }

    public static void main(String[] args) {
        String c1 = normalizeCode(" pen2026004251 ");
        System.out.println(validateAndFormat(c1));
        String c2 = normalizeCode("12N2026004251");
        System.out.println(validateAndFormat(c2));
        String c3 = normalizeCode("PEN2026004251");
        System.out.println(validateAndFormat(c3));
        String c4 = normalizeCode("PEN2026A04251");
        System.out.println(validateAndFormat(c4));
    }
}

