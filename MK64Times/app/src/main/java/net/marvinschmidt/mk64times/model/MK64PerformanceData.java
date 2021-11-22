package net.marvinschmidt.mk64times.model;

import java.util.ArrayList;
import java.util.Arrays;

public class MK64PerformanceData {

    private ArrayList<MK64TimeEntry> timeEntries;

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
        System.out.println("((get3LapTimesHundreds(false) + 3 * getfLapTimesHundreds(false)) / 525) -> " + ((get3LapTimesHundreds(false) + 3 * getfLapTimesHundreds(false)) / 525));
        System.out.println("(((get3LapTimesSeconds(false) * 100.0) + (3.0 * getfLapTimesSeconds(false) * 100.0)) / 525.0) ->" + (((get3LapTimesSeconds(false) * 100.0) + (3.0 * getfLapTimesSeconds(false) * 100.0)) / 525.0));
        System.out.println("(calculateAF() / 500 * 100) -> " + (calculateAF() / 500.0 * 100.0));
        System.out.println("(getTotalWRs() / 32) -> " + (getTotalWRs() / 32));
        return (((get3LapTimesSeconds(false) * 100.0) + (3.0 * getfLapTimesSeconds(false) * 100.0)) / 525.0) + (calculateAF() / 500 * 100) - ((double)getTotalWRs() / 32.0);
    }

    // TODO: AF = sum of ranks / 32
    public double calculateAF() {
        int sum = 0;
        for (MK64TimeEntry entry : timeEntries) {
            sum += entry.getRank();
        }
        return (double)sum / 32.0;
    }

    public double get3LapTimesSeconds(boolean pal) {
        double sum = 0;
        for (MK64TimeEntry entry : timeEntries)
            if (entry.is3Lap())
                sum += entry.getAsSeconds(pal);

        return sum;
    }

    public double getfLapTimesSeconds(boolean pal) {
        double sum = 0;
        for (MK64TimeEntry entry : timeEntries)
            if (!entry.is3Lap())
                sum += entry.getAsSeconds(pal);

        return sum;
    }

    public long get3LapTimesHundreds(boolean pal) {
        return (long) (get3LapTimesSeconds(pal) * 100.0);
    }

    public long getfLapTimesHundreds(boolean pal) {
        return (long) (getfLapTimesSeconds(pal) * 100.0);
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


}
