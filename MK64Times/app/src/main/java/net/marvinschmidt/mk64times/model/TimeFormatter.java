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

    public static double sumOfSeconds(ArrayList<MK64TimeEntry> entries, boolean roundIndividual, boolean roundFinal) {
        double sum = 0;
        for (MK64TimeEntry entry : entries)
            sum += new Time(entry).toSeconds(roundIndividual);

        return roundFinal ? (double) Math.round(sum) : sum;
    }


}
