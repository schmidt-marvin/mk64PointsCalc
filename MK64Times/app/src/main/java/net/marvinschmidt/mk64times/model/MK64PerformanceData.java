package net.marvinschmidt.mk64times.model;

import java.util.ArrayList;
import java.util.Arrays;

public class MK64PerformanceData {

    private ArrayList<MK64TimeEntry> timeEntries;

    private static final boolean SUM_ROUND_FINAL = false;


    public MK64PerformanceData(ArrayList<MK64TimeEntry> timeEntries) {
        if (timeEntries.size() != 32)
            throw new RuntimeException("Needs to contain all tracks");
        this.timeEntries = new ArrayList<>();
        this.timeEntries = (ArrayList<MK64TimeEntry>) timeEntries.clone();
    }

    public MK64PerformanceData(MK64TimeEntry[] entries) {
        if (entries.length != 32)
            throw new RuntimeException("Needs to contain all tracks");
        this.timeEntries = new ArrayList<>();
        timeEntries.addAll(Arrays.asList(entries)); // convert to ArrayList
    }

    public ArrayList<MK64TimeEntry> getTimeEntries() {
        return timeEntries;
    }

    //TODO: points = (total3laptime x 100 + 3 x totalflaptime x 100)/525 + AF/500 x 100 - WRcount/32
    public double calculatePoints() {
        //return (((Math.floor(get3LapTimesSeconds(false)) * 100.0) + (3.0 * Math.floor(getfLapTimesSeconds(false)) * 100.0)) / 525.0) + (calculateAF() / 500 * 100) - ((double)getTotalWRs() / 32.0);
        ArrayList<MK64TimeEntry> threeLapEntries = TimeFormatter.filter3lap(timeEntries);
        ArrayList<MK64TimeEntry> fastLapEntries = TimeFormatter.filterflap(timeEntries);



        return (TimeFormatter.sumOfSecondsForScore(threeLapEntries, SUM_ROUND_FINAL) * 100 +
                        3 * TimeFormatter.sumOfSecondsForScore(fastLapEntries, SUM_ROUND_FINAL) * 100) / 525
                + calculateAF() / 500 * 100
                - getTotalWRs() / 32;
    }

    // TODO: AF = sum of ranks / 32
    public double calculateAF() {
        int sum = 0;
        for (MK64TimeEntry entry : timeEntries) {
            sum += entry.getRank();
        }
        return (double)sum / 32.0;
    }


    public int getTotalWRs() {
        int sum = 0;
        for (MK64TimeEntry entry : timeEntries) {
            if (entry.isWR()) {
                sum += 1;
            }
        }
        return sum;
    }

    public String format3lapPALString() {
        Time total = new Time(0.0,0.0,0.0);

        for (MK64TimeEntry timeEntry : TimeFormatter.filter3lap(timeEntries)) {
            if (timeEntry.isPAL())
                total = Time.add(total, new Time(timeEntry));
            else
                total = Time.add(total, Time.ntscToPalConvert(new Time(timeEntry)));
        }

        return total.formatted(false, false, false);
    }

    public String formatflapPALString() {
        Time total = new Time(0.0,0.0,0.0);

        for (MK64TimeEntry timeEntry : TimeFormatter.filterflap(timeEntries)) {
            if (timeEntry.isPAL())
                total = Time.add(total, new Time(timeEntry));
            else
                total = Time.add(total, Time.ntscToPalConvert(new Time(timeEntry)));
        }

        return total.formatted(false, false, false);
    }

    public String format3lapNTSCString() {
        Time total = new Time(0.0,0.0,0.0);

        for (MK64TimeEntry timeEntry : TimeFormatter.filter3lap(timeEntries)) {
            if (timeEntry.isPAL())
                total = Time.add(total, Time.palToNtscConvert(new Time(timeEntry)));
            else
                total = Time.add(total, new Time(timeEntry));
        }

        return total.formatted(false, false, false);
    }

    public String formatflapNTSCString() {
        Time total = new Time(0.0,0.0,0.0);

        for (MK64TimeEntry timeEntry : TimeFormatter.filterflap(timeEntries)) {
            if (timeEntry.isPAL())
                total = Time.add(total, Time.palToNtscConvert(new Time(timeEntry)));
            else
                total = Time.add(total, new Time(timeEntry));
        }

        return total.formatted(false, false,false);
    }
}
