package net.marvinschmidt.mk64times.model;

import org.javatuples.Pair;
import org.javatuples.Triplet;
import org.javatuples.Tuple;

public class MK64TimeEntry {

    private String trackName;
    private int mins, secs, hundreds;
    private int rank;
    private boolean isPAL;
    private boolean isWR;

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

    public int getRank() {
        return rank;
    }

    public boolean is3Lap() {
        return trackName.contains("3lap");
    }

    public String getTrackName() {
        return trackName;
    }

    public boolean isPAL() {
        return isPAL;
    }

    public boolean isWR() {
        return isWR;
    }

    public int getMins() {
        return mins;
    }

    public int getSecs() {
        return secs;
    }

    public int getHundreds() {
        return hundreds;
    }

    public Triplet<Double, Double, Double> getTimeAsTriplet() {
        return Triplet.with((double)getMins(), (double)getSecs(), (double)getHundreds());
    }


}
