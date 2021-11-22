package net.marvinschmidt.mk64pointscalculator.model;

import java.time.LocalTime;

public class TimeFormatter {

    public static String formatTime(long hundreds) {
        long mins = hundreds / 6000;
        hundreds %= 6000;

        long secs = hundreds / 100;
        hundreds %= 100;

        String minsFormatted = String.format("%02d", mins);
        String secsFormatted = String.format("%02d", secs);
        String hundredsFormatted = String.format("%02d", hundreds);

        return minsFormatted + "\'" + secsFormatted + "\"" + hundredsFormatted;
    }

}
