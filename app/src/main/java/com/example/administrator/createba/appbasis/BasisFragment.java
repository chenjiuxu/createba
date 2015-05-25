package com.example.administrator.createba.appbasis;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by C.jiuxu on 2015/5/18.
 */
public abstract class BasisFragment extends Fragment {


    private FragmentCallbacks mactivity;
    public Bundle databundle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        databundle = getArguments();//获取activity传来的数据
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initial();
        logic();
        return LayoutInflater.from((BasisActivity) mactivity).inflate(setview(), null);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mactivity = (FragmentCallbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "must implement OnArticleSelectedListener");
        }
    }

    /**
     * 设置布局文件
     */
    public abstract int setview();

    /**
     * 初始化页面控件和数据
     */
    public abstract void initial();

    /**
     * 事物逻辑
     */
    public abstract void logic();

    /**
     * 数提交至Activity  用于Fragment之间的通讯
     */
    public void SubmitEventToActivity(Bundle bundle) {
        mactivity.callbacks(bundle);
    }

    public interface FragmentCallbacks {
        void callbacks(Bundle bundle);
    }


}
