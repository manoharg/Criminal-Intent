package com.manohar.root.crime;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by root on 12/12/17.
 */

public class DatePickerFragment extends DialogFragment {
    public  static  String ARG_DATE="date";
    private DatePicker mdatepicker;
    public  String SEND_DATE="senddate";
    public  static  DatePickerFragment newinstance(Date date)
    {
        Bundle b=new Bundle();
        b.putSerializable(ARG_DATE,date);
        DatePickerFragment f=new DatePickerFragment();
        f.setArguments(b);
        return  f;

    }
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar =Calendar.getInstance();
        Date date= (Date) getArguments().getSerializable(ARG_DATE);
        calendar.setTime(date);
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        View v= LayoutInflater.from(getActivity()).inflate(R.layout.date_picker,null);
        mdatepicker=v.findViewById(R.id.date_picker_dialog);
        mdatepicker.init(year,month,day,null);

         return  new AlertDialog.Builder(getActivity()).setView(v).setTitle("Date of Crime:").setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialogInterface, int i) {
                 int year=mdatepicker.getYear();
                 int month=mdatepicker.getMonth();
                 int day=mdatepicker.getDayOfMonth();
                 Date date=new GregorianCalendar(year,month,day).getTime();
                 sendresult(Activity.RESULT_OK,date);
             }
         }).create();

       // return super.onCreateDialog(savedInstanceState);
    }
    private void sendresult(int resultcode, Date date)
    {
        if(getTargetFragment()==null)
            return;
        Intent intent=new Intent();
        intent.putExtra(SEND_DATE,date);
        getTargetFragment().onActivityResult(getTargetRequestCode(),resultcode,intent);

    }
}
