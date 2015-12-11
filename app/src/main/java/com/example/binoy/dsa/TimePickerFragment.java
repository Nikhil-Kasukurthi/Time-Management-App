package com.example.binoy.dsa;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by binoy on 28-10-2015.
 */
public class TimePickerFragment extends Fragment {
    public static  final String WakeHourSP = "WakeHour";
    public static  final String WakeMinSP = "WakeMin";
    public static  final String StartHourSP = "StartHour";
    public static  final String StartMinSP = "StartMin";
    public static  final String EndHourSP = "EndHour";
    public static  final String EndMinSP = "EndMin";
    public static  final String SleepHourSP = "SleepHour";
    public static  final String SleepMinSP = "SleepMin";
    SharedPreferences sharedpreferences;
    android.widget.TimePicker time;
    Button next;
    TextView out;
    Boolean isWakeSet=false,isStartSet=false,isEndSet=false,isSleepSet=false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_time_picker,container, false);

        sharedpreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        time = (android.widget.TimePicker) rootView.findViewById(R.id.timePickerFragment);
        time.setIs24HourView(true);
        next = (Button) rootView.findViewById(R.id.nextButtonFragment);
        out = (TextView)rootView.findViewById(R.id.textViewTimePickerFragment);
        final SharedPreferences.Editor editor = sharedpreferences.edit();
      
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isWakeSet) {
                    int wakeHour = time.getCurrentHour();
                    int wakeMin = time.getCurrentMinute();
                    isWakeSet = true;
                    Toast.makeText(getActivity(), wakeHour + " : " + wakeMin, Toast.LENGTH_SHORT).show();
                    out.setText("When do your classes start?");
                    editor.putInt(WakeHourSP, wakeHour);
                    editor.putInt(WakeMinSP, wakeMin);
                    editor.commit();
                } else if (!isStartSet) {
                    int startHour = time.getCurrentHour();
                    int startMin = time.getCurrentMinute();
                    isStartSet = true;
                    Toast.makeText(getActivity(), startHour + " : " + startMin, Toast.LENGTH_SHORT).show();
                    out.setText("When do your classes end?");
                    editor.putInt(StartHourSP, startHour);
                    editor.putInt(StartMinSP, startMin);
                    editor.commit();
                } else if (!isEndSet) {
                    int endHour = time.getCurrentHour();
                    int endMin = time.getCurrentMinute();
                    isEndSet = true;
                    Toast.makeText(getActivity(), endHour + " : " + endMin, Toast.LENGTH_SHORT).show();
                    editor.putInt(EndHourSP, endHour);
                    editor.putInt(EndMinSP, endMin);
                    out.setText("When do you sleep?");
                    editor.commit();
                } else if(!isSleepSet){
                    int sleepHour = time.getCurrentHour();
                    int sleepMin = time.getCurrentMinute();
                    isEndSet = true;
                    Toast.makeText(getActivity(), sleepHour + " : " + sleepMin, Toast.LENGTH_SHORT).show();
                    if(sleepHour==0)
                        sleepHour=24;
                    editor.putInt(SleepHourSP, sleepHour);
                    editor.putInt(SleepMinSP, sleepMin);
                    editor.commit();
                    Intent intent = new Intent(getActivity(),MainScreen.class);
                    startActivity(intent);
                }
            }
        });
     return rootView;
                }
}
