package net.marvinschmidt.mk64times.ui.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import net.marvinschmidt.mk64times.R;
import net.marvinschmidt.mk64times.model.MK64TimeEntry;
import net.marvinschmidt.mk64times.model.Time;

import java.util.List;

public class TimeEntryAdapter extends ArrayAdapter<MK64TimeEntry> {

    private Context applicationContext;
    private int mResource;

    public TimeEntryAdapter(@NonNull Context context, int resource, @NonNull List<MK64TimeEntry> objects) {
        super(context, resource, objects);

        this.applicationContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String trackName = getItem(position).getTrackName();

        // get original time as formatted string
        String timeFormatted = "";
        MK64TimeEntry viewEntry = getItem(position);

        if (viewEntry.isPAL())
            timeFormatted = "Time: \t\t\t\t\t" + new Time(viewEntry).formatted(true, viewEntry.getRank() == 1);
        else
            timeFormatted = "Time: \t\t\t\t\t" + new Time(viewEntry).formatted(false, viewEntry.getRank() == 1);

        // get converted time as formatted string
        String timeConverted = "";
        if (viewEntry.isPAL())
            timeConverted = "Converted: \t" + Time.palToNtscConvert(new Time(viewEntry)).formatted(false, false);
        else
            timeConverted = "Converted: \t" + Time.ntscToPalConvert(new Time(viewEntry)).formatted(false, viewEntry.getRank() == 1);;

        String rankFormatted = "Rank: \t\t\t\t\t" + viewEntry.getRank();

        LayoutInflater inflater = LayoutInflater.from(applicationContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvleftText = convertView.findViewById(R.id.leftText);
        tvleftText.setText(trackName);

        TextView tvTopRightText = convertView.findViewById(R.id.topRightText);
        tvTopRightText.setText(timeFormatted);

        TextView tvmidRightText = convertView.findViewById(R.id.midRightText);
        tvmidRightText.setText(timeConverted);

        TextView tvBottomRightText = convertView.findViewById(R.id.bottomRightText);
        tvBottomRightText.setText(rankFormatted);

        return convertView;
    }


}
