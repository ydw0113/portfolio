package UnderWater;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import net.skhu.leisure.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class UnderWater_siseol extends AppCompatActivity {
    ViewPager viewPager;
    Adapter adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_underwater_sieol);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        ArrayList<String> list1 = new ArrayList<String>(Arrays.asList("풀장", "이동식 리프트", "왓츠실","유아적응실","샤워실"));
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
                        textView2.setText("풀장 :25m 레인 5개,최대 깊이는 130m");
                        textView.setText("- 장애인 고객 프로그램 시간에는 수온을 1도 정도 높게 유지합니다.\n자유 수영 시간에는 수상안전요원이 배치되어 고객의 \n안전한 이용을 돕고 있습니다.");
                        break;
                    case 1:
                        adapter2.notifyDataSetChanged(i);
                        textView2.setText("이동식 리프트");
                        textView.setText("-거동이 불편한 이용자가 안전하고, 편리하게 입수를 할 수 \n있도록 풀장 내에는 이동식 리프트가 설치되어 있습니다.");
                        break;
                    case 2:
                        adapter2.notifyDataSetChanged(i);
                        textView2.setText("왓츠(WATSU)");
                        textView.setText("-왓츠는 31℃~36℃ 사이의 따뜻한 물에서 심리적인 \n안정 상태를 유지하며 몸의 전신을 이완시킨 후 스트레칭, \n수중마사지, 지압 등을 장애에 따라 적용하여 중추신경계의 장애로 인한 신경근 마비 증세와 긴장도를 낮추어 주고,\n 관절가동 범위를 향상시키는 수중운동요법입니다 ");
                        break;
                    case 3:
                        adapter2.notifyDataSetChanged(i);
                        textView2.setText("유아적응실");
                        textView.setText("-유아적응실에서는 모자수중운동, 아동수중심리운동, 청소년 특수수영 프로그램이 진행됩니다. 수중재활센터는 보다 즐겁고,유익한 프로그램 진행을 위해서 다양한 놀이기구와 수십 개의 장난감을 보유하고 있습니다");
                        break;
                    case 4:
                        adapter2.notifyDataSetChanged(i);
                        textView2.setText("샤워실");
                        textView.setText("-서서 또는 앉아서 샤워를 할 수 있도록 \n수도꼭지가 탈의실 벽면 상하에 설치에 되어 있습니다");
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