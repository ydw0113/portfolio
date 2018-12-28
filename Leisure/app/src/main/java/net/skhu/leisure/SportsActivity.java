package net.skhu.leisure;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import DongCheon.DongcheonActivity;
import Gomduri.GomduriActivity;
import Mapo.MapoActivity;
import UnderWater.UnderWaterActivity;

public class SportsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        ListView listView = (ListView) findViewById(R.id.listview_sports);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position ==1) {
                    Intent intent = new Intent(getApplicationContext(), GomduriActivity.class);
                    startActivity(intent);

                }else if(position ==2) {
                    Intent intent = new Intent(getApplicationContext(), MapoActivity.class);
                    startActivity(intent);
                }
                else if(position ==3) {
                    Intent intent = new Intent(getApplicationContext(), DongcheonActivity.class);
                    startActivity(intent);
                }else if(position ==4) {
                    Intent intent = new Intent(getApplicationContext(), UnderWaterActivity.class);
                    startActivity(intent);
                }

            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

}