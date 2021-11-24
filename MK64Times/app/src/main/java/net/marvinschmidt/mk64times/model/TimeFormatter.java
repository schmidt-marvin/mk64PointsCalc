package net.marvinschmidt.mk64times.model;

import org.javatuples.Pair;
import org.javatuples.Triplet;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TimeFormatter {

    public static ArrayList<MK64TimeEntry> filter3lap(ArrayList<MK64TimeEntry> entries) {
        return new ArrayList<>(entries.stream()
                                      .filter(e -> e.is3Lap())
                                      .collect(Collectors.toList()));
    }

    public static ArrayList<MK64TimeEntry> filterflap(ArrayList<MK64TimeEntry> entries) {
        return new ArrayList<>(entries.stream()
                                      .filter(e -> !e.is3Lap())
                                      .collect(Collectors.toList()));
    }

    public static double sumOfSecondsForScore(ArrayList<MK64TimeEntry> entries, boolean roundFinal) {
        Time sum = new Time(0.0,0.0,0.0);
        for (MK64TimeEntry entry : entries) {
            if (entry.isPAL())
                sum = Time.add(sum, Time.palToNtscConvert(new Time(entry)));
            else
                sum = Time.add(sum, new Time(entry));
        }
        System.out.println("seconds for " + sum + " = " + sum.toSeconds(roundFinal));
        return sum.toSeconds(roundFinal);
    }


}
