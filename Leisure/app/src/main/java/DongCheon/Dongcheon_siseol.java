package DongCheon;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


import net.skhu.leisure.R;

import java.util.ArrayList;
import java.util.Arrays;

public class Dongcheon_siseol extends AppCompatActivity {
    ViewPager viewPager;
    Adapter adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dongcheon_siseol);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        ArrayList<String> list1 = new ArrayList<String>(Arrays.asList("스케이트장","헬스장"));
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        final TextView textView = (TextView) findViewById(R.id.explanation);
        final TextView textView2 = (TextView) findViewById(R.id.sizeText);
        adapter2 = new Adapter(this);
        viewPager = (ViewPager) findViewById(R.id.imgPager);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        adapter2.notifyDataSetChanged(i);
                        textView2.setText("스케이트장");
                        textView.setText("-스피드 스케이팅, 피겨스케이팅 강습장으로 활용");
                        break;
                    case 1:
                        adapter2.notifyDataSetChanged(i);
                        textView2.setText("헬스장");
                        textView.setText("-피트니스 프로그램에 사용");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                adapter2.instantiateItem(viewPager, 0);
                textView2.setText("162㎡(49평) 규모 ");
                textView.setText("-전면 대형거울 설치, 냉·난방 시설 완비, 충격완화 바닥설계 \n- 심리운동프로그램 등에 활용");
            }
        });


        viewPager.setAdapter(adapter2);


    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}