package com.aqoong.lib.objectflowviewsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.aqoong.lib.objectflowview.FlowObject;
import com.aqoong.lib.objectflowview.FlowObjectManager;
import com.aqoong.lib.objectflowview.ObjectFlowView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getSupportFragmentManager();
        setContentView(R.layout.activity_main);


        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, new TestFragment()).commitAllowingStateLoss();
    }

}
