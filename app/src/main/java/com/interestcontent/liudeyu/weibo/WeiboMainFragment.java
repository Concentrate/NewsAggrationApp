package com.interestcontent.liudeyu.weibo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.interestcontent.liudeyu.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeiboMainFragment extends Fragment {


    public WeiboMainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weibo_main, container, false);
    }

}
