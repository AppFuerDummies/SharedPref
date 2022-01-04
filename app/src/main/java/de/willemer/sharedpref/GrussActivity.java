package de.willemer.sharedpref;
/**
 * Diese Activity wird von der MainActivity per startActivity gerufen.
 * Sie soll die Daten aus den SharedPreferences lesen und darstellen.
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class GrussActivity extends AppCompatActivity {

    private String user = "";
    private int punkte = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Gruss", "onCreate");
        setContentView(R.layout.activity_gruss);
    }

    /**
     * Wird aufgerufen, wenn Activity den Fokus erlangt.
     */
    @Override
    public void onResume() {
        super.onResume();
        Log.d("Gruss", "onResume");
        // Lese die Daten aus den SharedPreferences
        SharedPreferences pref = getSharedPreferences("MeineApp", 0);
        punkte = pref.getInt("punkte", 0);
        user = pref.getString("user", "");
        boolean high = pref.getBoolean("HIGH", true);
        // Stelle die Daten in den TextViews dar.
        TextView tvuser = (TextView) findViewById(R.id.tvuser);
        TextView tvpunkte = (TextView) findViewById(R.id.tvpunkte);
        tvuser.setText(user);
        tvpunkte.setText(""+punkte);
    }

    /**
     * Wird aufgerufen, wenn Activity den Fokus verliert.
     */
    @Override
    public void onPause() {
        super.onPause();
        Log.d("Gruss", "onPause");
        // Schreibe die Daten in die SharedPreferences
        SharedPreferences pref = getSharedPreferences("MeineApp", 0);
        SharedPreferences.Editor ed = pref.edit();
        punkte++;
        ed.putInt("punkte", punkte);
        ed.commit(); // oder ed.apply();
    }

}