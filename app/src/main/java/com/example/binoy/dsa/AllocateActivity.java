package com.example.binoy.dsa;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class AllocateActivity extends Activity {

    ListView list;
    ArrayAdapter<String> adapter;
    String[] listContent={"Entertainment","Social","Club Activity","Studies"};
    public static final String ENTERTAINMENT = "Entertaiment";
    public static final String SOCIAL = "Social";
    public static final String STUDIES = "Studies";
    public static final String CLUB = "Club";
    SharedPreferences sharedpreferences;


    Button allocateButton,dialogButton;
    SeekBar seekBar;
    TextView textDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allocate);
        list =(ListView)findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(AllocateActivity.this, android.R.layout.simple_list_item_1, listContent);
        list.setAdapter(adapter);
        allocateButton = (Button)findViewById(R.id.allocateButton);

        textDialog = (TextView)findViewById(R.id.textViewDialog);
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_allocation);
        LayoutInflater inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View dialogLayout = inflater.inflate(R.layout.dialog_allocation,(ViewGroup)findViewById(R.id.root));
        dialog.setContentView(dialogLayout);
        sharedpreferences = PreferenceManager.getDefaultSharedPreferences(this);
        seekBar = (SeekBar)dialogLayout.findViewById(R.id.seekBarDialog);
        textDialog = (TextView)dialogLayout.findViewById(R.id.textViewDialog);
        dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
        final SharedPreferences.Editor editor = sharedpreferences.edit();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0: dialog.setTitle("Time for " + parent.getItemAtPosition(position));
                        dialog.show();
                        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                            int progressValue = 0,entertainment;
                            @Override
                            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                progressValue = progress;
                                textDialog.setText(String.valueOf(progressValue)+" Hour");
                                entertainment = progressValue;
                                editor.putInt(ENTERTAINMENT,entertainment);
                                editor.commit();
                            }

                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {

                            }

                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {
                            }
                        });
                        dialogButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        break;


                    case 1: dialog.setTitle("Time for " + parent.getItemAtPosition(position)+" Hour");
                        dialog.show();
                        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                            int progressValue = 0, social;

                            @Override
                            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                progressValue = progress;
                                textDialog.setText(String.valueOf(progressValue)+" Hour");
                                social = progressValue;
                                editor.putInt(SOCIAL, social);
                                editor.commit();
                            }

                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {

                            }

                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {
                            }
                        });
                        dialogButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        break;
                    case 2: dialog.setTitle("Time for "+  parent.getItemAtPosition(position));
                        dialog.show();
                        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                            int progressValue = 0,studies ;

                            @Override
                            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                progressValue = progress;
                                textDialog.setText(String.valueOf(progressValue)+" Hour");
                                studies = progressValue;
                                editor.putInt(STUDIES, studies);
                                editor.commit();
                            }

                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {

                            }

                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {
                            }
                        });
                        dialogButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        break;

                    case 3: dialog.setTitle("Time for "+  parent.getItemAtPosition(position)+" Hour");
                        dialog.show();
                        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                            int progressValue = 0, club;

                            @Override
                            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                progressValue = progress;
                                textDialog.setText(String.valueOf(progressValue)+" Hour");
                                club = progressValue;
                                editor.putInt(CLUB, club);
                                editor.commit();
                            }

                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {

                            }

                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {
                            }
                        });
                        dialogButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        break;
                    default: break;

                }
                allocateButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(AllocateActivity.this,MainScreen.class);
                        startActivity(intent);
                        AllocateActivity.this.finish();
                    }
                });
                Log.d("Ent", String.valueOf(sharedpreferences.getInt(ENTERTAINMENT, 0)));
                Toast.makeText(getApplicationContext(), parent.getItemAtPosition(position) + " is selected", Toast.LENGTH_SHORT).show();
            }

        });
    }
}
