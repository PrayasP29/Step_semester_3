package string_processing.class_problems;

public class FileExtensionValidator {

    static void validateFile(String filename) {
        int dot = filename.lastIndexOf('.');
        if (dot == -1 || dot == filename.length() - 1) {
            System.out.println("Rejected — invalid file type");
            return;
        }
        String ext = filename.substring(dot + 1);
        if (ext.equalsIgnoreCase("pdf") || ext.equalsIgnoreCase("docx") || ext.equalsIgnoreCase("zip")) {
            System.out.println("Accepted");
        } else {
            System.out.println("Rejected — invalid file type");
        }
    }

    public static void main(String[] args) {
        validateFile("Assignment1.PDF");
        validateFile("notes.txt");
        validateFile("archive.zip");
        validateFile("report.docx");
        validateFile("file");
    }
}

