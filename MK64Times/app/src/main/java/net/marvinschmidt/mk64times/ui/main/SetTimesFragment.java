package net.marvinschmidt.mk64times.ui.main;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import net.marvinschmidt.mk64times.R;
import net.marvinschmidt.mk64times.model.DataProducer;
import net.marvinschmidt.mk64times.model.JSONFormatter;
import net.marvinschmidt.mk64times.model.MK64TimeEntry;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SetTimesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SetTimesFragment extends Fragment {

    private ListView timesViewer;
    private SharedPreferences pref;
    private boolean loaded;
    private TimeEntryAdapter tea;
    ArrayList<MK64TimeEntry> timeEntries;


    public SetTimesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SetTimesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SetTimesFragment newInstance() {
        SetTimesFragment fragment = new SetTimesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_set_times, container, false);

        // save list view for action handling
        timesViewer = view.findViewById(R.id.timesViewer);

        //check, if time files exist and create empty set, if not.
        this.pref = getContext().getSharedPreferences("mk64te_prefs", Context.MODE_PRIVATE);

        if (!pref.contains("DATA")) {
            System.out.println("DATA doesn't exist in shared prefs, now populating empty set of data.");
            // some info for user here
        }

        loaded = false;

        // apply listeners to buttons

        Button handleSaveToDiskButton = view.findViewById(R.id.saveButton);
        handleSaveToDiskButton.setOnClickListener(e -> handleSaveToDiskButtonPressed());

        Button handleLoadFromDiskButton = view.findViewById(R.id.loadButton);
        handleLoadFromDiskButton.setOnClickListener(e -> handleLoadFromDiskButtonPressed());

        AdapterView.OnItemClickListener onItemClickListener = (adapterView, view1, i, l) ->
                showDialog(tea, i);
        timesViewer.setOnItemClickListener(onItemClickListener);


        return view;
    }

    public void showDialog(TimeEntryAdapter tea, int index) {
        ChangeTimeEntryDialog dialog = new ChangeTimeEntryDialog(tea, timeEntries, index);
        dialog.show(getActivity().getSupportFragmentManager(), "Time Entry Changer");
    }


    public void handleSaveToDiskButtonPressed() {
        if (loaded) {
            System.out.println("now writing to shared prefs");

            ArrayList<MK64TimeEntry> timeEntries = new ArrayList<>();

            try {
                System.out.println("reading list");
                Adapter adapter = timesViewer.getAdapter();
                for (int i=0; i < adapter.getCount(); i++){
                    timeEntries.add((MK64TimeEntry) adapter.getItem(i));
                }

                System.out.println("formatting json");
                String json = JSONFormatter.toJSONFormatted(timeEntries);

                System.out.println("writing to prefs");
                SharedPreferences.Editor edit = pref.edit();
                edit.putString("DATA", json);
                edit.commit();

                System.out.println("successful.");
                Toast.makeText(getContext(), "Times saved successfully.", Toast.LENGTH_SHORT).show();
            } catch (RuntimeException | JSONException e) {
                e.printStackTrace();
                System.out.println("error.");
            }
        } else {
            Toast.makeText(getContext(), "There is no content to be saved.", Toast.LENGTH_SHORT).show();
        }

    }

    public void handleLoadFromDiskButtonPressed() {
        if (loaded) {
            new AlertDialog.Builder(getContext())
                    .setTitle("Confirmation")
                    .setMessage("List has already been loaded and re-loading the data will overwrite any unsaved changes made in this window. Do you want to continue?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(android.R.string.yes, (dialog, button) ->
                            loadFromDisk())
                    .setNegativeButton(android.R.string.no, (dialog, button) ->
                            Toast.makeText(getContext(), "Action aborted.", Toast.LENGTH_SHORT).show()
            ).show();
        }
        else
            loadFromDisk();
    }

    public void loadFromDisk() {
        System.out.println("now reading from shared prefs");
        timeEntries = null;
        try {
            boolean exists = false;
            if (pref.contains("DATA"))
                exists = true;


            String json = pref.getString("DATA", JSONFormatter.toJSONFormatted(DataProducer.createEmptyDataSet().getTimeEntries()));
            System.out.println("JSON string loaded: \n" + json);
            timeEntries = JSONFormatter.fromJSONFormatted(json);

            if (exists)
                Toast.makeText(getContext(), "Times retrieved successfully.", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getContext(), "Created new set of times.", Toast.LENGTH_LONG).show();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        tea = new TimeEntryAdapter(this.getContext(), R.layout.adapter_listview_for_times, timeEntries);
        timesViewer.setAdapter(tea);

        loaded = true;
    }
}