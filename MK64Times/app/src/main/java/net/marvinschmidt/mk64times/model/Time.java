package net.marvinschmidt.mk64times.model;

import org.javatuples.Pair;
import org.javatuples.Triplet;

public class Time {

    private Triplet<Double, Double, Double> values;

    public Time(Double mins, Double secs, Double hundreds) {
        values = new Triplet<>(mins, secs, hundreds);
    }

    public Time(MK64TimeEntry entry) {
        values = new Triplet<>((double)entry.getMins(), (double)entry.getSecs(), (double)entry.getHundreds());
    }

    private double getMins() {
        return values.getValue0();
    }

    private double getSecs() {
        return values.getValue1();
    }

    private double getHundreds() {
        return values.getValue2();
    }

    private void setTime(Triplet<Double, Double, Double> time) {

    }

    public String toString() {
        return "mins = " + getMins() + ", secs = " + getSecs() + ", hundreds = " + getHundreds();
    }
    /**
     * takes a score played on NTSC and converts it to PAL
     * returned values are rounded to zero decimal places
     * uses calculations from view-source:https://www.mariokart64.com/mk64/convert.shtml
     */
    public static Time palToNtscConvert(Time palTime) {
        Double totalHundreds = palTime.toHundreds();

        // round total to two decimal places in conversion
        totalHundreds = (double) Math.round((Math.round(totalHundreds * 100.0)) / 1.2024 / 100.0);

        double ntscMinutes = Math.floor(totalHundreds/6000);
        double ntscSeconds = Math.floor((totalHundreds - ntscMinutes * 6000)/100);
        double ntscHundreds = (double)Math.round(totalHundreds - ntscMinutes * 6000 - ntscSeconds * 100);

        return new Time(ntscMinutes, ntscSeconds, ntscHundreds);
    }

    /**
     * takes a score played on PAL and converts it to NTSC
     * returned values are rounded to zero decimal places
     * uses calculations from view-source:https://www.mariokart64.com/mk64/convert.shtml
     */
    public static Time ntscToPalConvert(Time ntscTime) {
        Double totalHundreds = ntscTime.toHundreds();

        // round total to two decimal places in conversion
        totalHundreds = (double) Math.round((Math.round(totalHundreds * 100.0)) * 1.2024 / 100.0);

        double palMinutes = Math.floor(totalHundreds/6000);
        double palSeconds = Math.floor((totalHundreds - palMinutes * 6000)/100);
        double palHundreds = (double)Math.round(totalHundreds - palMinutes * 6000 - palSeconds * 100);

        return new Time(palMinutes, palSeconds, palHundreds);
    }

    public double toSeconds(boolean round) {
        Double totalSeconds = 0.0;
        totalSeconds += getMins() * 60;
        totalSeconds += getSecs();
        if (round && getHundreds() >= 50)
            totalSeconds += 1;
        else if (round)
            totalSeconds += 0;
        else
            totalSeconds += getHundreds() / 100;

        return totalSeconds;
    }

    public double toHundreds() {
        Double totalHundreds = 0.0;
        totalHundreds += getMins() * 6000;
        totalHundreds += getSecs() * 100;
        totalHundreds += getHundreds();

        return totalHundreds;
    }

    public static Time fromHundreds(double hundreds) {
        double calcMinutes = Math.floor(hundreds/6000);
        double calcSeconds = Math.floor((hundreds - calcMinutes * 6000)/100);
        double calcHundreds = (double)Math.round(hundreds - calcMinutes * 6000 - calcSeconds * 100);

        return new Time(calcMinutes, calcSeconds, calcHundreds);
    }

    public static Time add(Time A, Time B) {
        double mins = A.getMins() + + B.getMins();
        double secs = A.getSecs() + B.getSecs();
        double hundreds = A.getHundreds() + B.getHundreds();

        while (hundreds >= 100.0) {
            hundreds -= 100.0;
            secs += 1;
        }

        while (secs >= 60){
            secs -= 60;
            mins += 1;
        }

        return new Time(mins, secs, hundreds);
    }

    // included info string: (1) -> is pal? (2) -> is wr?
    // null -> no info
    public String formatted(boolean printIsPAL, boolean printIsNTSC, boolean printIsWR) {
        System.out.println("now formatting: " + this.toString());

        String minsFormatted = String.format("%02.0f", getMins());
        String secsFormatted = String.format("%02.0f", getSecs());
        String hundredsFormatted = String.format("%02.0f", getHundreds());

        String formatted = minsFormatted + "\'" + secsFormatted + "\"" + hundredsFormatted;
        if (printIsPAL || printIsWR) {
            // first value = is PAL time?
            if (printIsPAL)
                formatted += " (PAL)";
            else
                formatted += " (NTSC)";

            // second value = is WR?
            if (printIsWR)
                formatted += " (WR)";
        }
        return formatted;
    }
}
