package com.innopolis.lentanews;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainNewsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     //   View v = inflater.inflate(R.layout.main_news_fragment, container, false);
        View v = inflater.inflate(R.layout.main_news_fragment, null);
        return v;
    }
}
