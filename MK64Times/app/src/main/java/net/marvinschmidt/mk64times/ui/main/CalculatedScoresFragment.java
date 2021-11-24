package net.marvinschmidt.mk64times.ui.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import net.marvinschmidt.mk64times.R;
import net.marvinschmidt.mk64times.model.DataProducer;
import net.marvinschmidt.mk64times.model.JSONFormatter;
import net.marvinschmidt.mk64times.model.MK64PerformanceData;
import net.marvinschmidt.mk64times.model.MK64TimeEntry;
import net.marvinschmidt.mk64times.model.Time;
import net.marvinschmidt.mk64times.model.TimeFormatter;

import org.javatuples.Triplet;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.prefs.Preferences;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalculatedScoresFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalculatedScoresFragment extends Fragment{

    private View view;

    public CalculatedScoresFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CalculatedScoresFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalculatedScoresFragment newInstance() {
        CalculatedScoresFragment fragment = new CalculatedScoresFragment();
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
        this.view = inflater.inflate(R.layout.fragment_calculated_scores, container, false);

        // set listeners
        Button calculateButton = view.findViewById(R.id.calculateButton);
        calculateButton.setOnClickListener(e -> handleCalculateButtonPressed());

        return view;
    }

    public void handleCalculateButtonPressed() {
        // get data from shared storage
        SharedPreferences sPrefs = getContext().getSharedPreferences("mk64te_prefs", Context.MODE_PRIVATE);
        try {
            String json = sPrefs.getString("DATA", "na");
            if (json.equals("na"))
                Toast.makeText(getContext(), "Storage doesn't contain any data.", Toast.LENGTH_SHORT).show();
            else {
                ArrayList<MK64TimeEntry> timeEntries = JSONFormatter.fromJSONFormatted(json);
                MK64PerformanceData data = new MK64PerformanceData(timeEntries);

                changeFields(data.format3lapPALString(),
                        data.formatflapPALString(),
                        data.format3lapNTSCString(),
                        data.formatflapNTSCString(),
                        String.valueOf(data.calculateAF()),
                        String.format("%.6f", data.calculatePoints())
                );
                Toast.makeText(getContext(), "Calculated scores successfully.", Toast.LENGTH_SHORT).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public void changeFields(String total3lapPAL, String totalflapPAL, String total3lapNTSC, String totalflapNTSC,
                             String AF, String rankingPoints) {
        TextView tv_total3lapPAL = view.findViewById(R.id.text_value_01);
        tv_total3lapPAL.setText(total3lapPAL);

        TextView tv_totalflapPAL = view.findViewById(R.id.text_value_02);
        tv_totalflapPAL.setText(totalflapPAL);

        TextView tv_total3lapNTSC = view.findViewById(R.id.text_value_03);
        tv_total3lapNTSC.setText(total3lapNTSC);

        TextView tv_totalflapNTSC = view.findViewById(R.id.text_value_04);
        tv_totalflapNTSC.setText(totalflapNTSC);

        TextView tv_AF = view.findViewById(R.id.text_value_05);
        tv_AF.setText(AF);

        TextView tv_rankingPoints = view.findViewById(R.id.text_value_06);
        tv_rankingPoints.setText(rankingPoints);
    }
}