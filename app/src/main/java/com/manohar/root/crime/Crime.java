package com.manohar.root.crime;

import java.util.Date;
import java.util.UUID;

/**
 * Created by root on 7/12/17.
 */

public class Crime {
    private UUID mId;
    private  String mTitle;
private Date date;
private  boolean mSolved;
Crime()
{
    mId=UUID.randomUUID();
    date= new Date();

}
    public UUID getmId() {
        return mId;
    }
public  void setDate(Date date)
{
    this.date=date;
}

    public Date getDate() {
        return date;
    }



    public boolean ismSolved() {
        return mSolved;
    }

    public void setmSolved(boolean mSolved) {
        this.mSolved = mSolved;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }
}
