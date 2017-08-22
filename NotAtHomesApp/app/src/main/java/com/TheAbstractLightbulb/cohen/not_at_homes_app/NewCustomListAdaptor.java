package com.TheAbstractLightbulb.cohen.not_at_homes_app;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;



/**
 * Created by cohen on 30/06/2017.
 */

public class NewCustomListAdaptor extends CursorAdapter {

    public NewCustomListAdaptor(Context context, Cursor cursor, int flags){
        super(context, cursor, 0);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.activity_main, parent, false);



    }
}
