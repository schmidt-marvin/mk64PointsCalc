package net.marvinschmidt.mk64times.ui.main;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import net.marvinschmidt.mk64times.R;
import net.marvinschmidt.mk64times.model.MK64TimeEntry;

import java.util.ArrayList;

public class ChangeTimeEntryDialog extends AppCompatDialogFragment {

    private TextView trackNameText;
    private EditText minutesText, secondsText, hundredsText, rankText;
    private RadioGroup gameVersionRadioGroup;
    private RadioButton palRadioButton, ntscRadioButton;

    private TimeEntryAdapter tea;
    private ArrayList<MK64TimeEntry> entries;
    private int index;

    public ChangeTimeEntryDialog(TimeEntryAdapter tea, ArrayList<MK64TimeEntry> entries, int index) {
        this.tea = tea;
        this.entries = entries;
        this.index = index;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder bob = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.change_time_entry, null);

        bob.setView(view)
            .setTitle("Change time entry")
            .setNegativeButton("Cancel", ((DialogInterface.OnClickListener) (dialogInterface, i) -> {
                Toast.makeText(getContext(), "Cancelled change of time entry.", Toast.LENGTH_SHORT).show();
            }))
            .setPositiveButton("OK", ((DialogInterface.OnClickListener) (dialogInterface, i) -> {
                replaceTimeObject();
                Toast.makeText(getContext(), "Successful change of time entry.", Toast.LENGTH_SHORT).show();
            }));
        MK64TimeEntry entry = entries.get(index);

        trackNameText = view.findViewById(R.id.trackNameChanger);
        trackNameText.setText(entry.getTrackName());

        minutesText = view.findViewById(R.id.minChanger);
        minutesText.setText(String.valueOf(entry.getMins()));

        secondsText = view.findViewById(R.id.secChanger);
        secondsText.setText(String.valueOf(entry.getSecs()));

        hundredsText = view.findViewById(R.id.hundredsChanger);
        hundredsText.setText(String.valueOf(entry.getHundreds()));

        rankText = view.findViewById(R.id.rankChanger);
        rankText.setText(String.valueOf(entry.getRank()));

        palRadioButton = view.findViewById(R.id.radioPAL);
        ntscRadioButton = view.findViewById(R.id.radioNTSC);

        if (entry.isPAL()) {
            palRadioButton.setChecked(true);
        } else {
            ntscRadioButton.setChecked(true);
        }

        return bob.create();
    }

    public void replaceTimeObject() {

        String trackName = trackNameText.getText().toString();
        int mins = Integer.parseInt(minutesText.getText().toString());
        int secs = Integer.parseInt(secondsText.getText().toString());
        int hundreds = Integer.parseInt(hundredsText.getText().toString());
        int rank = Integer.parseInt(rankText.getText().toString());
        boolean pal = palRadioButton.isChecked();

        MK64TimeEntry newEntry = new MK64TimeEntry(trackName,
                mins,
                secs,
                hundreds,
                rank,
                pal,
                rank == 1);

        entries.set(index, newEntry);
        tea.notifyDataSetChanged();
    }
}
