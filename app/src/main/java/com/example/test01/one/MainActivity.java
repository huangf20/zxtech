package com.example.test01.one;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.test01.R;


public class MainActivity extends AppCompatActivity {

    TextView reader,activity;
    FrameLayout right;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ImmersionBar.with(this).statusBarDarkFont(true,0.1f).hideBar(BarHide.FLAG_HIDE_BAR).init();wenzi

        replaceFragment(new ReaderFragment());


    }



    private void replaceFragment(Fragment fragment)
    {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.rightlayout,fragment);
        transaction.commit();
    }


}
