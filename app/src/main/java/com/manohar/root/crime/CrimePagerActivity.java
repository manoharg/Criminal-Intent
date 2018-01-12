package com.manohar.root.crime;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;



/**
 * Created by root on 12/12/17.
 */

public class CrimePagerActivity extends AppCompatActivity {
 private List<Crime> crimes;
    public static String INTENT_TAG="new_intent";

 private ViewPager mpageviewer;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);
        crimes=CrimeLab.get(this).getCrimes();
        mpageviewer=findViewById(R.id.activity_crime_pager_viewer);
        final FragmentManager fm=getSupportFragmentManager();
        final UUID crimeId = (UUID) getIntent()
                .getSerializableExtra(INTENT_TAG);
        mpageviewer.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                Crime c=crimes.get(position);
                CrimeFragment f=CrimeFragment.newInstance(c.getmId());
               // fm.beginTransaction().add(R.id.fragment_container,f).commit();
                return f;
            }

            @Override
            public int getCount() {

                return crimes.size();
            }
        });
        for (int i = 0; i < crimes.size(); i++) {
            if (crimes.get(i).getmId().equals(crimeId)) {
                mpageviewer.setCurrentItem(i);
                break;
            }
        }
    }
    public  static Intent newintent(Context context, UUID id){
        Intent i=new Intent(context,CrimePagerActivity.class);
        i.putExtra(INTENT_TAG,id);
        return  i;

    }
}
