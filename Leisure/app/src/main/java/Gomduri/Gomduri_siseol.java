package Gomduri;

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

public class Gomduri_siseol extends AppCompatActivity {
    ViewPager viewPager;
    Adapter adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gomduri_siseol);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        ArrayList<String> list1 = new ArrayList<String>(Arrays.asList("심리운동실", "스피닝실", "사회문화교실", "골프연습장", "에어로빅실", "휘트니스실", "유아실", "미술실", "수영장1","수영장2", "재활체육실", "요가실", "체육관", "회의실", "장애인편의시설"));
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
                        textView2.setText("162㎡(49평) 규모 ");
                        textView.setText("- 전면 대형거울 설치,냉·난방 시설 완비,충격완화 바닥설계 \n- 심리운동프로그램 등에 활용");
                        break;
                    case 1:
                        adapter2.notifyDataSetChanged(i);
                        textView2.setText("66㎡(20평) 규모");
                        textView.setText("-실내싸이클과 음악, 댄스,조명이 함께하는 GX 프로그램 운영");
                        break;
                    case 2:
                        adapter2.notifyDataSetChanged(i);
                        textView2.setText("43㎡(13평) 규모  ");
                        textView.setText("-전자빔, 스크린 시설 완비 케이넥스, 종이접기, 음악활동 등에 활용");
                        break;
                    case 3:
                        adapter2.notifyDataSetChanged(i);
                        textView2.setText(" 247.5 ㎡ (75평) 규모 / 스크린 골프장 : 49.5㎡ (15평) 규모 ");
                        textView.setText("- 10개의 연습타석 완비(장애인 타석 3개 항시운영) \n- 최신시설의 스크린 골프연습실 운영 - 프로골퍼 2명 상시대기 지도");
                        break;
                    case 4:
                        adapter2.notifyDataSetChanged(i);
                        textView2.setText(" 198㎡(60평) 규모 ");
                        textView.setText("- 최고급 원목마루 시공, 전면 대형거울 부착, 음향장비 완비, 냉난방 가능\n- 에어로빅, 유아발레, 스포츠댄스,벨리댄스, 힙합 등에 활용");
                        break;
                    case 5:
                        adapter2.notifyDataSetChanged(i);
                        textView2.setText(" 297㎡(90평) 규모 ");
                        textView.setText("- 최신 헬스기구 보유, 대형거울 부착, 냉·난방 가능 , 지도선생님 상시 근무\n- 헬스기구 현황 : 유산소용(런닝머신 16대, 스텝머신 2대, 자전거 11대) / 웨이트용(토탈힙머신 외 33종) / \n프리웨이트용(벤치프렌스 외 18종) / 기타(벨트마시지 4대, 롤라마사지1대, 진동마사지 2대)\n- 체성분 및 체력 측정 무료");
                        break;
                    case 6:
                        adapter2.notifyDataSetChanged(i);
                        textView2.setText(" 247.5 ㎡ 새싹실 / 꿈나무실 각각82.5㎥(25평) 규모");
                        textView.setText("- 전면 통유리로 구성된 자연채광과 밝고 정감어린 교실 분위기 조성\n- 유아의 지능계발과 정서함양을 위한 교재·교구 및 놀이시설 구비\n- 냉·난방 시설 및 정수기와 식기살균소독기 설치\n- 유아 눈높이에 맞는 인테리어 및 환경 구성\n- 전자키보드 및 피아노 구비(음악실로 활용)");
                        break;
                    case 7:
                        adapter2.notifyDataSetChanged(i);
                        textView2.setText(" 36 ㎡(10평)규모");
                        textView.setText("- 미술실에 적합한 안전을 고려한 책상·의자 비치\n- 미술실 앞에 작품 발표공간 확보·전시\n- 화가입문, 레고닥터, 점프셈 등에 활용");
                        break;
                    case 8:
                        adapter2.notifyDataSetChanged(i);
                        textView2.setText(" 지상 1층 성인풀장 (깊이:1.3m, 길이:25m, 6레인) ");
                        textView.setText("비장애인과 장애인이 함께 수영할 수 있는 공간을 제공하고, 특히 장애인이 휠체어를 타고 수영장 풀에 직접 입수할 수 있도록 수영장 내에 경사로를 설치하였습니다. (풀장용 휠체어 상시 비치)");
                        break;
                    case 9:
                        adapter2.notifyDataSetChanged(i);
                        textView2.setText(" 지하2층 수영장 : 유아풀장 (깊이:70cm, 길이:15m, 4레인)/성인풀장 (깊이:1.3m, 길이:25m, 6레인) ");
                        textView.setText("유아풀장: 유아(2세~7세)들이 물에 대한 두려움을 느끼지 않도록 별도의 유아풀을 운영하고 있습니다.\n -성인풀장:유아풀과 별도로 성인풀장은 길이 25m, 6레인의 시설로서 초등학생도 성인과 동반하여 이용이 용이합니다");
                        break;
                    case 10:
                        adapter2.notifyDataSetChanged(i);
                        textView2.setText(" 67.5(20평) 규모");
                        textView.setText("- 휠체어 장애인 체력향상을 위한 프로그램 운영\n- 전동상·하지 치료기, 밴치프레스, 복합웨이트머신 설치");
                        break;
                    case 11:
                        adapter2.notifyDataSetChanged(i);
                        textView2.setText(" 94㎡(36평) 규모");
                        textView.setText("- 전방 대형유리 설치(운동시 자세교정 효과)\n- 냉·난방 시설 완비, 최신 음향 시설 확보, 충격완화 마루바닥\n- 요가, 키크는 요가, 임산부요가 등에 활용\n");
                        break;
                    case 12:
                        adapter2.notifyDataSetChanged(i);
                        textView2.setText(" 792㎡(240평) 규모");
                        textView.setText("- 최고급 원목마루 시공, 음향 조명 시설 완비\n- 농구, 배드민턴, 검도 등 실내 경기종목에 활용\n- 장애인 휠체어 농구 및 댄스에 활용");
                        break;
                    case 13:
                        adapter2.notifyDataSetChanged(i);
                        textView2.setText(" 66㎡(20평) 규모");
                        textView.setText("- 전자빔, 스크린 시설 완비 (파워포인트 활용 발표회 가능)\n- 케이넥스, 종이접기, 바이올린 등에 활용");
                        break;
                    case 14:
                        adapter2.notifyDataSetChanged(i);
                        textView2.setText(" 66㎡(20평) 규모");
                        textView.setText("거동이 불편한 장애인들을 위한 시설들");
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