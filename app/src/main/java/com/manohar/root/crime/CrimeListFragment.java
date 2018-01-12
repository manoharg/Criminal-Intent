package com.manohar.root.crime;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by root on 10/12/17.
 */

public class CrimeListFragment extends Fragment {
    private RecyclerView mrecycleview;
    private CrimeAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_crime_list,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.plus_button)
        {
            Crime c=new Crime();
            CrimeLab.get(getActivity()).addCrime(c);
            Intent i=CrimePagerActivity.newintent(getActivity(),c.getmId());
            startActivity(i);
            return true;

        }else
        return super.onOptionsItemSelected(item);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v= inflater.inflate(R.layout.fragement_crime_list,container,false);
        mrecycleview=v.findViewById(R.id.crime_recycle_view);
        mrecycleview.setLayoutManager(new LinearLayoutManager(getActivity()));
        UpdateUI();
        return  v;

    }
    public class  CrimeHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
    private TextView mTitle;
    private TextView mDate;
    private  CheckBox mSolved;
    private  Crime mCrime;
        public CrimeHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mTitle=itemView.findViewById(R.id.list_item_crime_title);
            mDate=itemView.findViewById(R.id.list_item_crime_date);
            mSolved=itemView.findViewById(R.id.list_item_crime_solved);

        }



        public void binddata(Crime crime)
        {
            mCrime=crime;
            mTitle.setText(crime.getmTitle());
            mDate.setText(crime.getDate().toString());
            mSolved.setChecked(crime.ismSolved());
        }


        @Override
        public void onClick(View view) {
            //Toast.makeText(getActivity(),mCrime.getmTitle()+" Solved",Toast.LENGTH_SHORT).show();
            Intent i= CrimePagerActivity.newintent(getActivity(),mCrime.getmId());
            startActivity(i);
        }
    }
    public  class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>
    {
        private List<Crime> mCrimes;
    CrimeAdapter(List<Crime> crimes)
    {
        mCrimes=crimes;
    }
        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater= LayoutInflater.from(getActivity());
            View v=layoutInflater.inflate(R.layout.list_item_crime,parent,false);
            return  new CrimeHolder(v);
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {
        Crime c=mCrimes.get(position);
            holder.binddata(c);
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }
    public void UpdateUI()
    {
        CrimeLab crimeLab=CrimeLab.get(getActivity());
        List<Crime> crimes=crimeLab.getCrimes();
        if(mAdapter==null) {
            mAdapter = new CrimeAdapter(crimes);
            mrecycleview.setAdapter(mAdapter);
        }
        else
        {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        UpdateUI();
    }
}
