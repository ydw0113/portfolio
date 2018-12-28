package Mapo;

import android.app.Activity;
import android.app.TabActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import android.widget.TabHost;

import net.skhu.leisure.R;

import java.util.ArrayList;

import Gomduri.Fragment1;
import Gomduri.Fragment2;
import Gomduri.Fragment3;


public class MapoProgram extends AppCompatActivity {
    private ViewPager mViewPager;
    ActionBar bar;
    private FragmentManager fm;
    private ArrayList<Fragment> fList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gomduriprogram);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        // 스와이프할 뷰페이저를 정의
        mViewPager = (ViewPager) findViewById(R.id.pager);

        // 프라그먼트 매니져 객체 정의
        fm = getSupportFragmentManager();

        // 액션바 객체 정의
        bar = getSupportActionBar();

        // 액션바 속성 정의
        bar.setDisplayShowTitleEnabled(true);   // 액션바 노출 유무
        bar.setTitle("마포푸르메스포츠센터 프로그램");   // 액션바 타이틀 라벨

        // 액션바에 모드 설정 = ActionBar.NAVIGATION_MODE_TABS 로 TAB 모드로 설정
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // 액션바에 추가될 탭 생성
        ActionBar.Tab tab1 = bar.newTab().setText("수중재활/심리운동").setTabListener(tabListener);
        ActionBar.Tab tab2 = bar.newTab().setText("장애인수영/특수체육").setTabListener(tabListener);
        ActionBar.Tab tab3 = bar.newTab().setText("통합수영/스포츠/건강문화").setTabListener(tabListener);


        // 액션바에 탭 추가
        bar.addTab(tab1);
        bar.addTab(tab2);
        bar.addTab(tab3 );

        // 각 탭에 들어갈 프라그먼트 생성 및 추가
        fList = new ArrayList<Fragment>();
        fList.add(Mapo.Fragment1.newInstance());
        fList.add(Mapo.Fragment2.newInstance());
        fList.add(Mapo.Fragment3.newInstance());

        // 스와이프로 탭간 이동할 뷰페이저의 리스너 설정
        mViewPager.setOnPageChangeListener(viewPagerListener);

        // 뷰페이져의 아답터 생성 및 연결
        CustomFragmentPagerAdapter adapter = new CustomFragmentPagerAdapter(fm, fList);
        mViewPager.setAdapter(adapter);

    }

    ViewPager.SimpleOnPageChangeListener viewPagerListener = new ViewPager.SimpleOnPageChangeListener(){
        @Override
        public void onPageSelected(int position) {
            super.onPageSelected(position);

            // 뷰페이저 이동시 해당 탭으로 이동
            bar.setSelectedNavigationItem(position);
        }
    };


    ActionBar.TabListener tabListener = new ActionBar.TabListener() {
        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
            // 해당 탭에서 벚어났을때 처리
        }

        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
            // 해당 탭을 선택시 처리
            // 해당 탭으로 뷰페이저도 이동
            mViewPager.setCurrentItem(tab.getPosition());
        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
            // 해당 탭이 다시 선택됐을때 처리
        }
    };
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

}