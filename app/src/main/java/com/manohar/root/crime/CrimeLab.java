package com.manohar.root.crime;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.manohar.root.crime.database.CrimeBaseHelper;
import com.manohar.root.crime.database.CrimeDbSchema;
import com.manohar.root.crime.database.CrimeDbSchema.CrimeTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by root on 10/12/17.
 */

public class CrimeLab {
    private static CrimeLab sCrimeLab;
   private List<Crime> mCrimes;

    private  Context mcontext;
    private SQLiteDatabase mdatabase;
    private ContentValues getContentValues(Crime c)
    {
        ContentValues values=new ContentValues();
        values.put(CrimeTable.Cols.DATE, String.valueOf(c.getDate()));
        values.put(CrimeTable.Cols.SOLVED,c.ismSolved()? 1:0);
        values.put(CrimeTable.Cols.TITLE,c.getmTitle());
        values.put(CrimeTable.Cols.UUID,c.getmId().toString());
        return  values;
    }
    private CrimeLab(Context context) {
        //mCrimes = new ArrayList<>();
       // mcontext=context.getApplicationContext();
        //mdatabase= new CrimeBaseHelper(mcontext).getWritableDatabase();
        for (int i = 0; i < 5; i++)

        {

            Crime a = new Crime();
            a.setmTitle("Crime" + i);
            a.setmSolved(i % 2 == 0);
            mCrimes.add(a);
        }
    }

    public void addCrime(Crime c) {
        mCrimes.add(c);
        //ContentValues v=getContentValues(c);
        //mdatabase.insert(CrimeTable.NAME,null,v);

    }
    public void updateCrime(Crime c)
    {
        //String uuid=c.getmId().toString();
        //mdatabase.update(CrimeTable.NAME,getContentValues(c),CrimeTable.Cols.UUID+"=?", new String[]{uuid});

    }

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    public List<Crime> getCrimes() {
        return mCrimes;
    }

    public Crime getCrime(UUID uid) {
        for (Crime crime : mCrimes) {
            if (crime.getmId().equals(uid)) {
                return crime;
            }
        }
        return null;

    }
}
