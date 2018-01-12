package com.manohar.root.crime;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.UUID;

public class CrimeActivity extends AppCompatActivity{
public static String INTENT_TAG="new_intent";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime);
        FragmentManager fm=getSupportFragmentManager();
        Fragment fragment= fm.findFragmentById(R.id.fragment_container);
        if(fragment==null)
        {
            fragment=new CrimeFragment();
            fm.beginTransaction().add(R.id.fragment_container,fragment).commit();
        }

    }
    public  static Intent newintent(Context context, UUID id){
        Intent i=new Intent(context,CrimeActivity.class);
        i.putExtra(INTENT_TAG,id);
        return  i;

    }
 }
