package net.marvinschmidt.mk64times.model;

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

    public static String formatted(long hundreds) {
        long mins = hundreds / 6000;
        hundreds %= 6000;

        long secs = hundreds / 100;
        hundreds %= 100;

        String minsFormatted = String.format("%02d", mins);
        String secsFormatted = String.format("%02d", secs);
        String hundredsFormatted = String.format("%02d", hundreds);

        return minsFormatted + "\'" + secsFormatted + "\"" + hundredsFormatted;
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

    public String getTimeFormatted() {
        String minsFormatted = String.format("%02d", this.mins);
        String secsFormatted = String.format("%02d", this.secs);
        String hundredsFormatted = String.format("%02d", this.hundreds);

        String formatted = isPAL() ? minsFormatted + "\'" + secsFormatted + "\"" + hundredsFormatted + " (PAL)" :
                minsFormatted + "\'" + secsFormatted + "\"" + hundredsFormatted + " (NTSC)";
        return isWR() ? formatted + " (WR)" : formatted;
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
}
