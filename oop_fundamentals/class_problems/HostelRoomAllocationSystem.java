package oop_fundamentals.class_problems;

public class HostelRoomAllocationSystem {

    static class HostelRoom {
        String roomNo;
        int beds;
        int occupied;

        HostelRoom(String roomNo, int beds, int occupied) {
            this.roomNo = roomNo;
            this.beds = beds;
            this.occupied = occupied;
        }

        void allot(String name) {
            if (occupied < beds) {
                occupied++;
                System.out.println(name + " allotted to room " + roomNo);
            } else {
                System.out.println("Room " + roomNo + " is full");
            }
        }
    }

    static HostelRoom findAvailableRoom(HostelRoom[] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i].occupied < rooms[i].beds) {
                return rooms[i];
            }
        }
        return null;
    }

    // passing HostelRoom[] does not copy HostelRoom objects; array holds references, objects stay shared
    static void safeAllot(HostelRoom[] rooms, String studentName) {
        HostelRoom r = findAvailableRoom(rooms);
        if (r != null) {
            r.allot(studentName);
        } else {
            System.out.println("No rooms available for " + studentName);
        }
    }

    public static void main(String[] args) {
        HostelRoom[] rooms1 = {
            new HostelRoom("C-214", 3, 2),
            new HostelRoom("C-507", 2, 2)
        };
        safeAllot(rooms1, "Divya");

        HostelRoom[] rooms2 = {
            new HostelRoom("C-214", 2, 2),
            new HostelRoom("C-507", 3, 3)
        };
        safeAllot(rooms2, "Divya");

        // null check never NPE
        HostelRoom none = findAvailableRoom(rooms2);
        System.out.println("findAvailableRoom returned null? " + (none == null));
    }
}
