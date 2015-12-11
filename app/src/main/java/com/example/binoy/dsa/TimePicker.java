package com.example.binoy.dsa;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TimePicker extends Activity {

    android.widget.TimePicker time;
    Button next;
    TextView out;

    boolean isWakeSet,isStartSet,isEndSet,isSleepSet;
    public static  final String isWakeSetSP = "isWakeTimeKey";
    public static  final String isStartSetSP = "isStartTimeKey";
    public static  final String isEndSetSP = "isEndTimeKey";
    public static  final String isSleepSetSP = "isSetTimeKey";
    public static  final String WakeHourSP = "WakeHour";
    public static  final String WakeMinSP = "WakeMin";
    public static  final String StartHourSP = "StartHour";
    public static  final String StartMinSP = "StartMin";
    public static  final String EndHourSP = "EndHour";
    public static  final String EndMinSP = "EndMin";
    public static  final String SleepHourSP = "SleepHour";
    public static  final String SleepMinSP = "SleepHour";
    Intent intent;
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker);
        time = (android.widget.TimePicker) findViewById(R.id.timePicker);
        time.setIs24HourView(true);

        next=(Button)findViewById(R.id.button);
        out=(TextView)findViewById(R.id.textView);

        sharedpreferences = PreferenceManager.getDefaultSharedPreferences(this);

        isWakeSet = sharedpreferences.getBoolean(isWakeSetSP, false);
        isStartSet= sharedpreferences.getBoolean(isStartSetSP,false);
        isEndSet = sharedpreferences.getBoolean(isEndSetSP,false);
        isSleepSet = sharedpreferences.getBoolean(isSleepSetSP,false);

        if(sharedpreferences.getBoolean(isWakeSetSP,false)){
            intent = new Intent(TimePicker.this,MainScreen.class);
            startActivity(intent);
            TimePicker.this.finish();
        }

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                if (!isWakeSet) {
                    int wakeHour = time.getCurrentHour();
                    int wakeMin = time.getCurrentMinute();
                    isWakeSet = true;
                    Toast.makeText(TimePicker.this, wakeHour + " : " + wakeMin, Toast.LENGTH_SHORT).show();
                    out.setText("When do your classes start?");
                    editor.putBoolean(isWakeSetSP, isWakeSet);
                    editor.putInt(WakeHourSP, wakeHour);
                    editor.putInt(WakeMinSP, wakeMin);
                    editor.commit();
                } else if (!isStartSet) {
                    int startHour = time.getCurrentHour();
                    int startMin = time.getCurrentMinute();
                    isStartSet = true;
                    Toast.makeText(TimePicker.this, startHour + " : " + startMin, Toast.LENGTH_SHORT).show();
                    out.setText("When do your classes end?");
                    editor.putBoolean(isStartSetSP, isStartSet);
                    editor.putInt(TimePicker.StartHourSP, startHour);
                    editor.putInt(StartMinSP, startMin);
                    editor.commit();
                } else if (!isEndSet) {
                    int endHour = time.getCurrentHour();
                    int endMin = time.getCurrentMinute();
                    isEndSet = true;
                    Toast.makeText(TimePicker.this, endHour + " : " + endMin, Toast.LENGTH_SHORT).show();
                    out.setText("When do you sleep?");
                    editor.putBoolean(isEndSetSP, isEndSet);
                    editor.putInt(EndHourSP, endHour);
                    editor.putInt(EndMinSP, endMin);
                    editor.commit();
                } else if(!isSleepSet){
                    int sleepHour = time.getCurrentHour();
                    int sleepMin = time.getCurrentMinute();
                    isEndSet = true;
                    Toast.makeText(TimePicker.this, sleepHour + " : " + sleepMin, Toast.LENGTH_SHORT).show();
                    editor.putBoolean(isEndSetSP, isEndSet);
                    if(sleepHour==0)
                        sleepHour=24;
                    editor.putInt(EndHourSP, sleepHour);
                    editor.putInt(EndMinSP, sleepMin);
                    editor.commit();
                    intent = new Intent(TimePicker.this,AllocateActivity.class);
                    startActivity(intent);
                    TimePicker.this.finish();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_time_picker, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
