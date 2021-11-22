package net.marvinschmidt.mk64times.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONFormatter {

    public static String toJSONFormatted(ArrayList<MK64TimeEntry> timeEntries) throws JSONException {
        JSONObject root = new JSONObject();

        JSONArray times = new JSONArray();

        for (MK64TimeEntry entry : timeEntries) {
            JSONObject obj = new JSONObject();
            obj.put("trackName", entry.getTrackName());
            obj.put("mins", entry.getMins());
            obj.put("secs", entry.getSecs());
            obj.put("hundreds", entry.getHundreds());
            obj.put("rank", entry.getRank());
            obj.put("isPAL", entry.isPAL());
            obj.put("isWR", entry.isWR());
            times.put(obj);
        }

        root.put("timeEntries", times);
        return root.toString(2);
    }

    public static ArrayList<MK64TimeEntry> fromJSONFormatted(String json) throws JSONException {
        JSONObject root = new JSONObject(json);

        JSONArray jsonTimeEntries = root.getJSONArray("timeEntries");
        ArrayList<MK64TimeEntry> timeEntries = new ArrayList<>();

        for (int i = 0; i < jsonTimeEntries.length(); i++) {
            JSONObject jsonTimeEntry = jsonTimeEntries.getJSONObject(i);

            String trackName = jsonTimeEntry.getString("trackName");
            int mins = jsonTimeEntry.getInt("mins");
            int secs = jsonTimeEntry.getInt("secs");
            int hundreds = jsonTimeEntry.getInt("hundreds");
            int rank = jsonTimeEntry.getInt("rank");
            boolean isPAL = jsonTimeEntry.getBoolean("isPAL");
            boolean isWR = jsonTimeEntry.getBoolean("isWR");

            MK64TimeEntry entry = new MK64TimeEntry(trackName, mins, secs, hundreds, rank, isPAL, isWR);
            timeEntries.add(entry);
        }

        return timeEntries;
    }
}
