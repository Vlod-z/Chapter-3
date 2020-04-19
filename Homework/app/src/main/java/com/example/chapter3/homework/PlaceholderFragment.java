package com.example.chapter3.homework;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.List;

public class PlaceholderFragment extends Fragment {
    private LottieAnimationView lottieview;
    private ListView listview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件
        View view=inflater.inflate(R.layout.fragment_placeholder, container, false);
        lottieview = (LottieAnimationView) view.findViewById(R.id.lottie_view);
        listview=(ListView) view.findViewById(R.id.list_view);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,getData());
        listview.setAdapter(arrayAdapter);
        return view;
    }

    private List<String> getData(){
        List<String> data = new ArrayList<String>();
        for(int i = 1;i <=7;i++) {
            data.add("Item "+i);
        }
        return data;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 5s 后执行
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入
                ObjectAnimator animator1=ObjectAnimator.ofFloat(lottieview,"alpha",1,0);
                animator1.setRepeatCount(0);
                animator1.setDuration(500);
                animator1.setInterpolator(new LinearInterpolator());
                ObjectAnimator animator2=ObjectAnimator.ofFloat(listview,"alpha",0,1);
                animator2.setRepeatCount(0);
                animator2.setDuration(500);
                animator2.setInterpolator(new LinearInterpolator());
                AnimatorSet animatorSet=new AnimatorSet();
                animatorSet.playTogether(animator1,animator2);
                animatorSet.start();
            }
        }, 5000);
    }
}
