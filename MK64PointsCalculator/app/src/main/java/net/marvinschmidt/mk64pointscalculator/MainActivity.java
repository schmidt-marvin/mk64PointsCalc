package net.marvinschmidt.mk64pointscalculator;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import net.marvinschmidt.mk64pointscalculator.databinding.ActivityMainBinding;
import net.marvinschmidt.mk64pointscalculator.model.MK64PerformanceData;
import net.marvinschmidt.mk64pointscalculator.model.MK64TimeEntry;
import net.marvinschmidt.mk64pointscalculator.model.TimeFormatter;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        test();
    }

    public void test() {
        MK64TimeEntry[] times = new MK64TimeEntry[]{
                new MK64TimeEntry("Luigi Raceway 3lap", 1, 59, 78, 142, true, false),
                new MK64TimeEntry("Luigi Raceway flap", 0, 38, 10, 144, true, false),

                new MK64TimeEntry("Moo Moo Farm 3lap", 1, 29, 86, 146, true, false),
                new MK64TimeEntry("Moo Moo Farm flap", 0, 29, 10, 182, true, false),

                new MK64TimeEntry("Koopa Troopa Beach 3lap", 1, 38, 55, 167, true, false),
                new MK64TimeEntry("Koopa Troopa Beach flap", 0, 31, 75, 175, true, false),

                new MK64TimeEntry("Kalimari Desert 3lap", 2, 8, 4, 162, true, false),
                new MK64TimeEntry("Kalimari Desert flap", 0, 39, 97, 171, true, false),

                new MK64TimeEntry("Toad's Turnpike 3lap", 3, 1, 25, 82, true, false),
                new MK64TimeEntry("Toad's Turnpike flap", 0, 59, 73, 88, true, false),

                new MK64TimeEntry("Frappe Snowland 3lap", 2, 2, 59, 87, true, false),
                new MK64TimeEntry("Frappe Snowland flap", 0, 38, 92, 62, true, false),

                new MK64TimeEntry("Choco Mountain 3lap", 1, 59, 23, 138, true, false),
                new MK64TimeEntry("Choco Mountain flap", 0, 38, 98, 165, true, false),

                new MK64TimeEntry("Mario Raceway 3lap", 1, 30, 6, 161, true, false),
                new MK64TimeEntry("Mario Raceway flap", 0, 28, 21, 159, true, false),

                new MK64TimeEntry("Wario Stadium 3lap", 4, 28, 91, 132, true, false),
                new MK64TimeEntry("Wario Stadium flap", 1, 28, 83, 153, true, false),

                new MK64TimeEntry("Sherbet Land 3lap", 1, 58, 29, 80, true, false),
                new MK64TimeEntry("Sherbet Land flap", 0, 38, 56, 59, true, false),

                new MK64TimeEntry("Royal Raceway 3lap", 2, 55, 21, 116, true, false),
                new MK64TimeEntry("Royal Raceway flap", 0, 56, 78, 105, true, false),

                new MK64TimeEntry("Bowser's Castle 3lap", 2, 16, 15, 137, true, false),
                new MK64TimeEntry("Bowser's Castle flap", 0, 44, 36, 141, true, false),

                new MK64TimeEntry("D.K.'s Jungle Parkway 3lap", 2, 19, 45, 140, true, false),
                new MK64TimeEntry("D.K.'s Jungle Parkway flap", 0, 43, 70, 119, true, false),

                new MK64TimeEntry("Yoshi Valley 3lap", 1, 47, 9, 146, true, false),
                new MK64TimeEntry("Yoshi Valley flap", 0, 32, 44, 163, true, false),

                new MK64TimeEntry("Banshee Boardwalk 3lap", 2, 6, 97, 88, true, false),
                new MK64TimeEntry("Banshee Boardwalk flap", 0, 41, 86, 156, true, false),

                new MK64TimeEntry("Rainbow Road 3lap", 6, 0, 69, 91, true, false),
                new MK64TimeEntry("Rainbow Road flap", 1, 58, 91, 77, true, false),

        };
        MK64PerformanceData performanceData = new MK64PerformanceData(times);

        System.out.println("AF: " + performanceData.calculateAF());
        System.out.println("total 3lap time PAL: " + TimeFormatter.formatTime(performanceData.get3LapTimesHundreds(true)) + "(" + performanceData.get3LapTimesHundreds(true) + " hundreds)");
        System.out.println("total flap time PAL: " + TimeFormatter.formatTime(performanceData.getfLapTimesHundreds(true)) + "(" + performanceData.getfLapTimesHundreds(true) + " hundreds)");
        System.out.println("total 3lap time NTSC: " + TimeFormatter.formatTime(performanceData.get3LapTimesHundreds(false)) + "(" + performanceData.get3LapTimesHundreds(false) + " hundreds)");
        System.out.println("total flap time NTSC: " + TimeFormatter.formatTime(performanceData.getfLapTimesHundreds(false)) + "(" + performanceData.getfLapTimesHundreds(false) + " hundreds)");
        System.out.println("Points: " + performanceData.calculatePoints());
    }

}