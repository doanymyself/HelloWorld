package demo.helloworld.framework;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * *********************************************************
 * 基类Fragment,可自动继承各变量
 * *********************************************************
 * <p>
 * Created by wangdong on 2016/5/16.
 */
@SuppressLint("ValidFragment")
public class BaseFragment extends Fragment {
    protected FragmentTransaction mFragmentTransaction;
    protected MyApplication myApplication;
    protected Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        context = getActivity();
        mFragmentTransaction = getActivity().getSupportFragmentManager()
                .beginTransaction();
        myApplication = MyApplication.myApplication;
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUIandEvent();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    protected void initUIandEvent() {

    }

    protected void deInitUIandEvent() {

    }
}
