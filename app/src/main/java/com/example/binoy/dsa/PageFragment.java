package com.example.binoy.dsa;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class PageFragment extends Fragment {
    TextView time, activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parentViewGroup,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.viewpager_fragment, parentViewGroup, false);
        time = (TextView) rootView.findViewById(R.id.time);
        activity = (TextView) rootView.findViewById(R.id.activity);
        setData();
        return rootView;
    }

    public void setData() {
        time.setText(getArguments().getString("Time"));
        activity.setText(getArguments().getString("Activity"));
    }
}

