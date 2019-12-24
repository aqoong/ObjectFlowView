package com.aqoong.lib.objectflowviewsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

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

//        vObjectFlow = findViewById(R.id.flowview);
//        vObjectFlow.setFlowObjectManager(setData());



        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, new TestFragment()).commitAllowingStateLoss();
    }

//    private FlowObjectManager setData(){
//        FlowObject[] objects = new FlowObject[10];
//        for(int i = 0 ; i < 10 ; i++){
//            FlowObject object = new FlowObject();
//            object.setType(FlowObject.ObjectType.TYPE_TEXT);
//            object.setStrText("TEST입니다. index : "+i);
//            objects[i] = object;
//        }
//
//        FlowObjectManager manager = new FlowObjectManager(getApplicationContext(), objects);
//        return manager;
//    }
}
