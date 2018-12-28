package Mapo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.skhu.leisure.R;

/**
 * Created by Administrator on 2016-10-11.
 */
public class Fragment3 extends Fragment {

    public static Fragment3 newInstance(){
        Fragment3 fragment = new Fragment3();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.mfragment3,container,false);

        return view;
    }
}