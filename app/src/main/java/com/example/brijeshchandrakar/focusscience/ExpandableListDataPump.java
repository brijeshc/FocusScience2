package com.example.brijeshchandrakar.focusscience;

import android.database.Cursor;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by brijesh.chandrakar on 19/10/16.
 */

public class ExpandableListDataPump {
        private static String intro;
        private static String detail;
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        public static HashMap<String, String> getData(ScienceReaderHelper scdb) {
            HashMap<String, String> expandableListDetail = new HashMap<String, String>();
            for (int i=1; i<3; i++)
            {
                intro = "";
                detail = "";
                Cursor rs = scdb.getDataCategory(i);
                rs.moveToFirst();
                Log.d("Count",String.valueOf(rs.getCount()));
                if(rs.getCount() > 0){
                // get values from cursor here
                    intro = rs.getString(rs.getColumnIndex("Category"));
                }
                if (!rs.isClosed())
                {
                    rs.close();
                }
                rs = scdb.getDataTitle(i);
                rs.moveToFirst();
                Log.d("Count",String.valueOf(rs.getCount()));
                if(rs.getCount() > 0){
                    // get values from cursor here
                    intro = intro + ": " + rs.getString(rs.getColumnIndex("Title"));
                }
                if (!rs.isClosed())
                {
                    rs.close();
                }
                rs = scdb.getDataDate(i);
                rs.moveToFirst();
                Log.d("Count",String.valueOf(rs.getCount()));
                if(rs.getCount() > 0){
                    // get values from cursor here
                    intro = intro + "..." + rs.getString(rs.getColumnIndex("Date"));
                }
                if (!rs.isClosed())
                {
                    rs.close();
                }
                rs = scdb.getDataAbstract(i);
                rs.moveToFirst();
                Log.d("Count",String.valueOf(rs.getCount()));
                if(rs.getCount() > 0){
                    // get values from cursor here
                    detail = rs.getString(rs.getColumnIndex("Abstract"));
                }
                if (!rs.isClosed())
                {
                    rs.close();
                }

                expandableListDetail.put(intro, detail);
            }

            return expandableListDetail;
        }
}

