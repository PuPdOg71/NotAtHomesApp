package com.TheAbstractLightbulb.cohen.not_at_homes_app;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.EditText;

/**
 * Created by cohen on 20/06/2017.
 */

public class NewListAdapter extends CursorAdapter {

    public NewListAdapter(Context context, Cursor cursor, int flag){

        super(context, cursor, 0);
    }

    public void bindView(View view, Context context, Cursor cursor){
        EditText location  = (EditText)view.findViewById(R.id.ListName2);
        String name = cursor.getString(cursor.getColumnIndex("Locatin"));
        location.setText(name);
    }

    public View  newView(Context context, Cursor cursor, ViewGroup parent){
        return LayoutInflater.from(context).inflate(R.layout.edit_data,  parent, false);

    }
}
