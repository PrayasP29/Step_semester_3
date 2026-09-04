package oop_fundamentals.assigment_problems;

public class LibraryMembershipStaticDesignSystem {

    static class BrokenLibraryMember {
        static String name;
        static String memberId;
        static int booksIssued;

        BrokenLibraryMember(String n, String id, int b) {
            name = n;
            memberId = id;
            booksIssued = b;
        }
    }

    static class LibraryMember {
        String name;
        String memberId;
        int booksIssued;

        static String libraryName = "SRM Central Library";
        static int memberCount = 1000;

        LibraryMember(String name, int booksIssued) {
            this.name = name;
            this.booksIssued = booksIssued;
            memberCount++;
            this.memberId = "LM-" + memberCount;
        }

        void printMemberCard() {
            System.out.println(name + " | " + memberId);
        }

        static void printTotalMembers() {
            System.out.println("Total members: " + (memberCount - 1000));
        }
    }

    public static void main(String[] args) {
        System.out.println("Broken version:");
        BrokenLibraryMember b1 = new BrokenLibraryMember("Aditi", "LM-1001", 2);
        BrokenLibraryMember b2 = new BrokenLibraryMember("Rohan", "LM-1002", 3);
        System.out.println(BrokenLibraryMember.name);
        System.out.println(BrokenLibraryMember.name);
        // why incorrect:
        // static name: shared, not per member
        // static memberId: shared, not per member
        // static booksIssued: shared, not per member

        System.out.println("\nFixed version:");
        LibraryMember m1 = new LibraryMember("Aditi", 2);
        LibraryMember m2 = new LibraryMember("Rohan", 1);
        m1.printMemberCard();
        m2.printMemberCard();
        LibraryMember.printTotalMembers();
    }
}
