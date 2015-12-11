package com.example.binoy.dsa;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by binoy on 28-10-2015.
 */
public class MainScreenFragment extends Fragment {

    public ViewPager viewPager;
    public SliderAdapter adapter;
    public static int SLOTS[][]={
            {1,2,3,4,1,2,3,4,1,2,3,4,1,2,3,4,1,2,3,4,1,2,3,4},
            {2,2,1,4,4,1,3,2,2,1,3,4,2,3,1,2,3,1,4,1,2,3,3,1},
            {4,4,2,3,4,3,2,1,4,1,2,3,2,1,4,3,2,3,1,4,1,2,4,2},
            {3,2,1,2,3,1,4,1,3,1,4,1,2,1,4,1,2,3,1,2,3,1,3,2}
    };
    public int priorityArray[]=new int[24];

    public static final String ENTERTAINMENT = "Entertaiment";
    public static final String SOCIAL = "Social";
    public static final String STUDIES = "Studies";
    public static final String CLUB = "Club";
    public static  final String WakeHourSP = "WakeHour";
    public static  final String StartHourSP = "StartHour";
    public static  final String EndHourSP = "EndHour";
    public static  final String SleepHourSP = "SleepHour";
    SharedPreferences sharedpreferences;
    int wakeHour,startHour,endHour,sleepHour,entertainment,social,club,studies;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_screen, container, false);
         viewPager=(ViewPager)rootView.findViewById(R.id.pager);
        allocate();
        return rootView;
    }

    public void allocate(){
        sharedpreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        wakeHour = sharedpreferences.getInt(WakeHourSP,0);
        startHour =sharedpreferences.getInt(StartHourSP,0);
        endHour = sharedpreferences.getInt(EndHourSP,0);
        entertainment = sharedpreferences.getInt(ENTERTAINMENT, 0);
        social = sharedpreferences.getInt(SOCIAL, 0);
        club = sharedpreferences.getInt(CLUB,0);
        studies = sharedpreferences.getInt(STUDIES,0);
        sleepHour = sharedpreferences.getInt(SleepHourSP,0);
        int ent=1,soc=2,clu=3,stud=4;

        for(int i=0;i<4;i++){
            int arr[]=SLOTS[i];
            for(int j=0;j<24;j++){
                if((j>=wakeHour&&j<startHour)||(j>=endHour&&j<sleepHour)) {
                    if (arr[j] == ent && priorityArray[j] == 0 && entertainment > 0) {
                        priorityArray[j] = ent;
                        entertainment--;
                    } else if (arr[j] == soc && priorityArray[j] == 0 && social > 0) {
                        priorityArray[j] = soc;
                        social--;
                    } else if (arr[j] == clu && priorityArray[j] == 0 && club > 0) {
                        priorityArray[j] = clu;
                        club--;
                    } else if (arr[j] == stud && priorityArray[j] == 0 && studies > 0) {
                        priorityArray[j] = stud;
                        studies--;
                    }
                }
                else{
                    priorityArray[j]=5;
                }
            }
        }
        for(int h=0;h<priorityArray.length;h++)
        adapter=new SliderAdapter(getChildFragmentManager(),priorityArray.length);
        viewPager.setAdapter(adapter);

    }

    private class SliderAdapter extends FragmentStatePagerAdapter {
        PageFragment pageFragment;
        int count;
        public SliderAdapter(FragmentManager supportFragmentManager,int c) {
            super(supportFragmentManager);
            count=c;
        }
        @Override
        public Fragment getItem(int position) {
            pageFragment=new PageFragment();
            Bundle bundle =new Bundle();
            String t;
            String a="";
            t=position+"-"+(position+1);
            if(priorityArray[position]==5)
                a="Busy Hour";
            else if(priorityArray[position]==1){
                a="Entertainment";
            }
            else if(priorityArray[position] == 2) {
                a ="Social";
            }
            else if(priorityArray[position]==3){
                a="Club Activity";
            }
            else if(priorityArray[position]==4){
                a="Studies";
            }
            else if(priorityArray[position]==0){
                a="Unallocated";
            }
            bundle.putString("Time",t);
            bundle.putString("Activity",a);
            pageFragment.setArguments(bundle);
            return pageFragment;
        }

        @Override

        public int getCount() {
            return count;
        }
    }
}
