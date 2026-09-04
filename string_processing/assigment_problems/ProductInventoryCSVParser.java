package string_processing.assigment_problems;

public class ProductInventoryCSVParser {

    static void parseRecord(String csv) {
        String[] parts = csv.split(",");
        if (parts.length != 3) {
            System.out.println("Invalid Record");
            return;
        }
        String product = parts[0].trim();
        String sku = parts[1].trim();
        String qty = parts[2].trim();
        System.out.println("Product: " + product + " | SKU: " + sku + " | Qty: " + qty);
    }

    public static void main(String[] args) {
        parseRecord("Wireless Mouse,WM-2201,150");
        parseRecord("Wireless Mouse,150");
        parseRecord("Keyboard,KB-001,75");
    }
}

