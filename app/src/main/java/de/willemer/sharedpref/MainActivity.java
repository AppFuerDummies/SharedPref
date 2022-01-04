package de.willemer.sharedpref;
/**
 * Diese App dient zur Demonstration der SharedPreferences.
 * Sie werden in onPause gesichert und in onResume geladen.
 * In der GrussActivity werden sie ebenfalls verwendet, so dass
 * ein Datenaustausch zwischen den Activities stattfindet.
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Diese beiden Variableninhalte werden per
    // SharedPreferences gesichert:
    private String user = "Eulalia";
    private int punkte = 0;
    // Diese beiden Variablen werden per Bundle gesichert
    private String bstr = "Bundle-String";
    private int bint = 42;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Main", "onCreate");
        // Über den Parameter erhält die Activity die in
        // onSaveInstanceState gesichert wurden
        if (savedInstanceState!=null) {
            bstr = savedInstanceState.getString("str");
            bint = savedInstanceState.getInt("log");
        }
        setContentView(R.layout.activity_main);
        Button bt = (Button) findViewById(R.id.button);
        bt.setOnClickListener(this);
    }

    /**
     * Methode wird aufgerufen, wenn die Activity den Fokus verliert.
     * Hier werden die Variablen in den SharedPreferences gesichert.
     */
    @Override
    public void onPause() {
        super.onPause();
        Log.d("Main", "onPause");
        SharedPreferences pref = getSharedPreferences("MeineApp", 0);
        SharedPreferences.Editor ed = pref.edit();
        ed.putString("user", user);
        ed.commit(); // oder ed.apply();
    }

    /**
     * Methode wird aufgerufen, wenn die Activity den Fokus erhält.
     * Hier werden die Variablen aus den SharedPreferences ausgelesen.
     */
    @Override
    public void onResume() {
        super.onResume();
        Log.d("Main", "onResume");
        SharedPreferences pref = getSharedPreferences("MeineApp", 0);
        punkte = pref.getInt("punkte", 0);
        TextView tvPunkte = (TextView) findViewById(R.id.tvPunkte);
        tvPunkte.setText(""+punkte);
    }

    @Override
    public void onClick(View view) {
        EditText ed = (EditText) findViewById(R.id.edUser);
        user = ed.getText().toString();
        Intent intent = new Intent(this, GrussActivity.class);
        startActivity(intent);

    }

    /**
     * Sichert den Status der Activity beim Herunterfahren im Bundle.
     */
    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Log.d("Main", "onSaveInstanceState");
        outState.putString("str", bstr);
        outState.putInt("log", bint);
    }
}