package com.example.brijeshchandrakar.focusscience;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import static com.example.brijeshchandrakar.focusscience.R.id.expandableListView;

public class MainActivity extends Activity {

    private ImageView splashImageView;
    boolean splashloading = false;
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, String> expandableListDetail;
    private ScienceReaderHelper scdb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        splashImageView = new ImageView(this);
        splashImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        splashImageView.setImageResource(R.drawable.ic_launcher);
        setContentView(splashImageView);
        splashloading = true;
        Handler h = new Handler();
        scdb = new ScienceReaderHelper(this);
        h.postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            public void run() {
                splashloading = false;
                setContentView(R.layout.activity_main);
                expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
                expandableListDetail = ExpandableListDataPump.getData(scdb);
                expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
                expandableListAdapter = new CustomExpandableListAdapter(MainActivity.this, expandableListTitle, expandableListDetail);
                expandableListView.setAdapter(expandableListAdapter);
                expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

                    @Override
                    public void onGroupExpand(int groupPosition) {
                        Toast.makeText(getApplicationContext(),
                                expandableListTitle.get(groupPosition) + " List Expanded.",
                                Toast.LENGTH_SHORT).show();
                    }
                });

                expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

                    @Override
                    public void onGroupCollapse(int groupPosition) {
                        Toast.makeText(getApplicationContext(),
                                expandableListTitle.get(groupPosition) + " List Collapsed.",
                                Toast.LENGTH_SHORT).show();

                    }
                });

                expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                    @Override
                    public boolean onChildClick(ExpandableListView parent, View v,
                                                int groupPosition, int childPosition, long id) {
                        sendurl(groupPosition);
                        Log.d("GroupPos",String.valueOf(groupPosition));
                        return false;
                    }
                });



            }

        }, 4000);


}
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void sendurl(int position)
    {
        Intent intent = new Intent(this, WebArticle.class);
        String url = "";
        Cursor rs = scdb.getDataUrl(position);
        rs.moveToFirst();
        Log.d("Count",String.valueOf(rs.getCount()));
        if(rs.getCount() > 0){
            // get values from cursor here
           url = rs.getString(rs.getColumnIndex("Url"));
        }
        if (!rs.isClosed())
        {
            rs.close();
        }
        intent.putExtra("Url", url);
        startActivity(intent);
    }

    //load some initial data into out list



}

