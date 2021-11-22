package net.marvinschmidt.mk64pointscalculator.model;

public class MK64TimeEntry {

    private String trackName;
    private int mins, secs, hundreds;
    private int rank;
    boolean isPAL;
    boolean isWR;

    // Name convention just like on https://beckabney.com/mk64/
    // examples: "Luigi Raceway 3lap", "Luigi Raceway flap" and so on.
    public MK64TimeEntry(String trackName, int mins, int secs, int hundreds, int rank, boolean isPAL, boolean isWR) {
        this.trackName = trackName;
        this.mins = mins;
        this.secs = secs;
        this.hundreds = hundreds;
        this.rank = rank;
        this.isPAL = isPAL;
        this.isWR = isWR;
    }

    // (https://beckabney.com/mk64/sully/conversion_rate.html)
    public double getSeconds(boolean pal) {
        if (pal) {
            if (isPAL) {
                return (mins * 60) + secs + ((double)hundreds) / 100.0;
            }
            else
                return ((mins * 60) + secs + ((double)hundreds) / 100.0) * 1.2024;
        } else {
            if (!isPAL)
                return (mins * 60) + secs + ((double)hundreds) / 100.0;
            else
                return ((mins * 60) + secs + ((double)hundreds) / 100.0) / 1.2024;
        }
    }


    public int getRank() {
        return rank;
    }

    public boolean is3Lap() {
        return trackName.contains("3lap");
    }



}
