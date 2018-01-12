package com.manohar.root.crime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.Date;
import java.util.UUID;

/**
 * Created by root on 7/12/17.
 */

public class CrimeFragment extends Fragment
{
    private EditText medittext;
    private Button mCrimebutton;
    private CheckBox mIssolved;
    public  int REQUEST_CODE=0;
    public  String SEND_DATE="senddate";
    public  static  String DATE_TAG="date_tag";
    private  Crime mCrime;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //mCrime=new Crime();
        //UUID mId= (UUID) getActivity().getIntent().getSerializableExtra(CrimeActivity.INTENT_TAG);
        UUID mId = (UUID) getArguments().getSerializable(CrimePagerActivity.INTENT_TAG);
        mCrime=CrimeLab.get(getActivity()).getCrime(mId);
    }
    public static CrimeFragment newInstance(UUID crimeId) {
        Bundle args = new Bundle();
        args.putSerializable(CrimePagerActivity.INTENT_TAG, crimeId);

        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragement_crime,container,false);
        medittext=v.findViewById(R.id.fragement_edittext);
        medittext.setText(mCrime.getmTitle());
        mCrimebutton=v.findViewById(R.id.crime_button);
        updateDate(mCrime.getDate());
        // mCrimebutton.setEnabled(false);
        mCrimebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm=getFragmentManager();

                DatePickerFragment dp=DatePickerFragment.newinstance(mCrime.getDate());
                dp.setTargetFragment(CrimeFragment.this, REQUEST_CODE);
                dp.show(fm,DATE_TAG);
            }
        });
        mIssolved=v.findViewById(R.id.crime_solved);
        mIssolved.setChecked(mCrime.ismSolved());

        mIssolved.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mCrime.setmSolved(b);
            }
        });
        medittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mCrime.setmTitle(medittext.getText().toString());
            }
        });
       // medittext.setText(medittext.getText());

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if(resultCode!= Activity.RESULT_OK)
            return;
        if(requestCode==REQUEST_CODE)
        {

            Date date= (Date) data.getSerializableExtra(SEND_DATE);
            updateDate(date);
            mCrime.setDate(date);
        }


    }

    private void updateDate(Date date) {
        mCrimebutton.setText(date.toString());
    }

    @Override
    public void onPause() {
        super.onPause();
        CrimeLab.get(getActivity()).updateCrime(mCrime);
    }
}
