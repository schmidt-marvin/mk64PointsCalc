package net.marvinschmidt.mk64times.model;

public class DataProducer {

    public static MK64PerformanceData createSampleData() {
        MK64TimeEntry[] times = new MK64TimeEntry[]{
                new MK64TimeEntry("Luigi Raceway 3lap", 1, 59, 78, 142, true, false),
                new MK64TimeEntry("Luigi Raceway flap", 0, 38, 10, 144, true, false),

                new MK64TimeEntry("Moo Moo Farm 3lap", 1, 29, 86, 146, true, false),
                new MK64TimeEntry("Moo Moo Farm flap", 0, 29, 10, 182, true, false),

                new MK64TimeEntry("Koopa Troopa Beach 3lap", 1, 38, 55, 167, true, false),
                new MK64TimeEntry("Koopa Troopa Beach flap", 0, 31, 75, 175, true, false),

                new MK64TimeEntry("Kalimari Desert 3lap", 2, 8, 4, 162, true, false),
                new MK64TimeEntry("Kalimari Desert flap", 0, 39, 97, 171, true, false),

                new MK64TimeEntry("Toad's Turnpike 3lap", 3, 1, 25, 82, true, false),
                new MK64TimeEntry("Toad's Turnpike flap", 0, 59, 73, 88, true, false),

                new MK64TimeEntry("Frappe Snowland 3lap", 2, 2, 59, 87, true, false),
                new MK64TimeEntry("Frappe Snowland flap", 0, 38, 92, 62, true, false),

                new MK64TimeEntry("Choco Mountain 3lap", 1, 59, 23, 138, true, false),
                new MK64TimeEntry("Choco Mountain flap", 0, 38, 98, 165, true, false),

                new MK64TimeEntry("Mario Raceway 3lap", 1, 30, 6, 161, true, false),
                new MK64TimeEntry("Mario Raceway flap", 0, 28, 21, 159, true, false),

                new MK64TimeEntry("Wario Stadium 3lap", 4, 28, 91, 132, true, false),
                new MK64TimeEntry("Wario Stadium flap", 1, 28, 83, 153, true, false),

                new MK64TimeEntry("Sherbet Land 3lap", 1, 58, 29, 80, true, false),
                new MK64TimeEntry("Sherbet Land flap", 0, 38, 56, 59, true, false),

                new MK64TimeEntry("Royal Raceway 3lap", 2, 55, 21, 116, true, false),
                new MK64TimeEntry("Royal Raceway flap", 0, 56, 78, 105, true, false),

                new MK64TimeEntry("Bowser's Castle 3lap", 2, 16, 15, 137, true, false),
                new MK64TimeEntry("Bowser's Castle flap", 0, 44, 36, 141, true, false),

                new MK64TimeEntry("D.K.'s Jungle Parkway 3lap", 2, 19, 45, 140, true, false),
                new MK64TimeEntry("D.K.'s Jungle Parkway flap", 0, 43, 70, 119, true, false),

                new MK64TimeEntry("Yoshi Valley 3lap", 1, 47, 9, 146, true, false),
                new MK64TimeEntry("Yoshi Valley flap", 0, 32, 44, 163, true, false),

                new MK64TimeEntry("Banshee Boardwalk 3lap", 2, 6, 97, 88, true, false),
                new MK64TimeEntry("Banshee Boardwalk flap", 0, 41, 86, 156, true, false),

                new MK64TimeEntry("Rainbow Road 3lap", 6, 0, 69, 91, true, false),
                new MK64TimeEntry("Rainbow Road flap", 1, 58, 91, 77, true, false)
        };
        return new MK64PerformanceData(times);
    }

    public static MK64PerformanceData createEmptyDataSet() {
        MK64TimeEntry[] times = new MK64TimeEntry[]{
                new MK64TimeEntry("Luigi Raceway 3lap", 9, 59, 99, 9999, true, false),
                new MK64TimeEntry("Luigi Raceway flap", 9, 59, 99, 9999, true, false),

                new MK64TimeEntry("Moo Moo Farm 3lap", 9, 59, 99, 9999, true, false),
                new MK64TimeEntry("Moo Moo Farm flap", 9, 59, 99, 9999, true, false),

                new MK64TimeEntry("Koopa Troopa Beach 3lap", 9, 59, 99, 9999, true, false),
                new MK64TimeEntry("Koopa Troopa Beach flap", 9, 59, 99, 9999, true, false),

                new MK64TimeEntry("Kalimari Desert 3lap", 9, 59, 99, 9999, true, false),
                new MK64TimeEntry("Kalimari Desert flap", 9, 59, 99, 9999, true, false),

                new MK64TimeEntry("Toad's Turnpike 3lap", 9, 59, 99, 9999, true, false),
                new MK64TimeEntry("Toad's Turnpike flap", 9, 59, 99, 9999, true, false),

                new MK64TimeEntry("Frappe Snowland 3lap", 9, 59, 99, 9999, true, false),
                new MK64TimeEntry("Frappe Snowland flap", 9, 59, 99, 9999, true, false),

                new MK64TimeEntry("Choco Mountain 3lap", 9, 59, 99, 9999, true, false),
                new MK64TimeEntry("Choco Mountain flap", 9, 59, 99, 9999, true, false),

                new MK64TimeEntry("Mario Raceway 3lap", 9, 59, 99, 9999, true, false),
                new MK64TimeEntry("Mario Raceway flap", 9, 59, 99, 9999, true, false),

                new MK64TimeEntry("Wario Stadium 3lap", 9, 59, 99, 9999, true, false),
                new MK64TimeEntry("Wario Stadium flap", 9, 59, 99, 9999, true, false),

                new MK64TimeEntry("Sherbet Land 3lap", 9, 59, 99, 9999, true, false),
                new MK64TimeEntry("Sherbet Land flap", 9, 59, 99, 9999, true, false),

                new MK64TimeEntry("Royal Raceway 3lap", 9, 59, 99, 9999, true, false),
                new MK64TimeEntry("Royal Raceway flap", 9, 59, 99, 9999, true, false),

                new MK64TimeEntry("Bowser's Castle 3lap", 9, 59, 99, 9999, true, false),
                new MK64TimeEntry("Bowser's Castle flap", 9, 59, 99, 9999, true, false),

                new MK64TimeEntry("D.K.'s Jungle Parkway 3lap", 9, 59, 99, 9999, true, false),
                new MK64TimeEntry("D.K.'s Jungle Parkway flap", 9, 59, 99, 9999, true, false),

                new MK64TimeEntry("Yoshi Valley 3lap", 9, 59, 99, 9999, true, false),
                new MK64TimeEntry("Yoshi Valley flap", 9, 59, 99, 9999, true, false),

                new MK64TimeEntry("Banshee Boardwalk 3lap", 9, 59, 99, 9999, true, false),
                new MK64TimeEntry("Banshee Boardwalk flap", 9, 59, 99, 9999, true, false),

                new MK64TimeEntry("Rainbow Road 3lap", 9, 59, 99, 9999, true, false),
                new MK64TimeEntry("Rainbow Road flap", 9, 59, 99, 9999, true, false),
        };
        return new MK64PerformanceData(times);
    }

}
