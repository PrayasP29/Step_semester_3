package constructors_java_keywords.class_problems;

public class BusRouteRankingEngine {

    static class BusRoute {
        String routeCode;
        String routeName;
        int priority;

        public BusRoute(String routeCode, String routeName, int priority) {
            this.routeCode = routeCode;
            this.routeName = routeName;
            this.priority = priority;
        }

        public BusRoute(String routeCode, String routeName) {
            this(routeCode, routeName, 5);
        }

        int compareTo(BusRoute other) {
            if (other == null) return 1;
            // 1) priority ascending (lower number = higher priority)
            if (this.priority != other.priority) {
                return Integer.compare(this.priority, other.priority);
            }
            // 2) routeCode case-insensitive
            int codeCmp = this.routeCode.compareToIgnoreCase(other.routeCode);
            if (codeCmp != 0) return codeCmp;
            // 3) routeName case-insensitive
            int nameCmp = this.routeName.compareToIgnoreCase(other.routeName);
            if (nameCmp != 0) return nameCmp;
            // completely tied -> preserve input order (return 0, stable sort will keep order)
            return 0;
        }

        public String toString() {
            return routeCode + " " + routeName + " " + priority;
        }
    }

    static BusRoute[] rankRoutes(BusRoute[] routes) {
        if (routes == null) return new BusRoute[0];
        BusRoute[] copy = new BusRoute[routes.length];
        for (int i = 0; i < routes.length; i++) copy[i] = routes[i];

        // Insertion sort stable O(n^2), deterministic, preserves input order on ties
        for (int i = 1; i < copy.length; i++) {
            BusRoute key = copy[i];
            int j = i - 1;
            while (j >= 0 && copy[j].compareTo(key) > 0) {
                copy[j + 1] = copy[j];
                j--;
            }
            copy[j + 1] = key;
        }
        return copy;
    }

    public static void main(String[] args) {
        BusRoute[] routes = {
            new BusRoute("R002", "Chennai", 2),
            new BusRoute("r001", "Bangalore", 1),
            new BusRoute("R001", "Bangalore", 1),
            new BusRoute("R003", "Pune", 3),
            new BusRoute("R004", "Delhi", 2)
        };
        BusRoute[] ranked = rankRoutes(routes);
        for (int i = 0; i < ranked.length; i++) {
            System.out.println(ranked[i]);
        }
        // default priority
        BusRoute def = new BusRoute("R005", "Mumbai");
        System.out.println("Default priority: " + def.priority);

        // case-insensitive check: R001 and r001 should tie on code, preserve input order
        BusRoute[] tie = {
            new BusRoute("R001", "Alpha", 1),
            new BusRoute("r001", "Alpha", 1)
        };
        BusRoute[] rankedTie = rankRoutes(tie);
        System.out.println("Tie order: " + rankedTie[0].routeCode + " then " + rankedTie[1].routeCode);
    }
}

