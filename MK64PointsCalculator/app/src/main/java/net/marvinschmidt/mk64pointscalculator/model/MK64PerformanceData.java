package net.marvinschmidt.mk64pointscalculator.model;

public class MK64PerformanceData {

    private MK64TimeEntry[] timeEntries;

    public MK64PerformanceData(MK64TimeEntry[] entries) {
        if (entries.length != 32)
            throw new RuntimeException("Needs to contain all tracks");
        this.timeEntries = entries;
    }

    //TODO: points = (total3laptime x 100 + 3 x totalflaptime x 100)/525 + AF/500 x 100 - WRcount/32
    public double calculatePoints() {
        System.out.println("((get3LapTimesSeconds(false) * 100 + 3 * getfLapTimesSeconds(false) * 100) / 525) -> " + ((get3LapTimesSeconds(false) * 100 + 3 * getfLapTimesSeconds(false) * 100) / 525));
        System.out.println("(calculateAF() / 5) -> " + (calculateAF() / 5));
        System.out.println("(getTotalWRs() / 32) -> " + (getTotalWRs() / 32));
        return (((get3LapTimesSeconds(false) * 100) + (3 * getfLapTimesSeconds(false) * 100)) / 525) + (calculateAF() / 5) - ((double)getTotalWRs() / 32);
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
                sum += entry.getSeconds(pal);

        return sum;
    }

    public double getfLapTimesSeconds(boolean pal) {
        double sum = 0;
        for (MK64TimeEntry entry : timeEntries)
            if (!entry.is3Lap())
                sum += entry.getSeconds(pal);

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
            if (entry.isWR) {
                sum += 1;
            }
        }
        return sum;
    }

    public static void main(String[] args) {

    }
}
