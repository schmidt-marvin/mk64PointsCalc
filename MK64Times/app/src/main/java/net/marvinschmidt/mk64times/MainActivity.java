package net.marvinschmidt.mk64times;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import net.marvinschmidt.mk64times.model.DataProducer;
import net.marvinschmidt.mk64times.model.JSONFormatter;
import net.marvinschmidt.mk64times.ui.main.SectionsPagerAdapter;
import net.marvinschmidt.mk64times.databinding.ActivityMainBinding;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);

        // set up with filled data
        /*
        SharedPreferences prefs = getSharedPreferences("mk64te_prefs", Context.MODE_PRIVATE);
        try {
            prefs.edit().putString("DATA", JSONFormatter.toJSONFormatted(DataProducer.createSampleData().getTimeEntries())).commit();
        } catch (JSONException e) {
            e.printStackTrace();
        }
         */
    }
}