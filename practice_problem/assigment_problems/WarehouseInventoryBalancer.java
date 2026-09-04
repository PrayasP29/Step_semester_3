package practice_problem.assigment_problems;


public class WarehouseInventoryBalancer {

    static void analyzeInventory(int[] sectionA, int[] sectionB) {
        int totalA = 0;
        for (int i = 0; i < sectionA.length; i++) {
            totalA += sectionA[i];
        }

        int totalB = 0;
        for (int i = 0; i < sectionB.length; i++) {
            totalB += sectionB[i];
        }

        System.out.println("Section A Total: " + totalA);
        System.out.println("Section B Total: " + totalB);

        if (totalA == totalB) {
            System.out.println("Status: Balanced");
        } else {
            System.out.println("Status: Not Balanced");
        }

        if (sectionA.length == 0 && sectionB.length == 0) {
            System.out.println("No inventory data");
            return;
        }

        int highest = 0;
        String section = "";
        int itemNumber = 0;
        boolean first = true;

        for (int i = 0; i < sectionA.length; i++) {
            if (first || sectionA[i] > highest) {
                highest = sectionA[i];
                section = "A";
                itemNumber = i + 1;
                first = false;
            }
        }

        for (int i = 0; i < sectionB.length; i++) {
            if (first || sectionB[i] > highest) {
                highest = sectionB[i];
                section = "B";
                itemNumber = i + 1;
                first = false;
            }
        }

        System.out.println("Highest Quantity: " + highest + " (Section " + section + ", Item " + itemNumber + ")");
    }

    public static void main(String[] args) {
        int[] sectionA = {20, 15, 30};
        int[] sectionB = {25, 10, 30};
        analyzeInventory(sectionA, sectionB);

        System.out.println();

        int[] sectionA2 = {10, 20, 30};
        int[] sectionB2 = {5, 5, 5};
        analyzeInventory(sectionA2, sectionB2);
    }
}
