package com.aqoong.lib.objectflowviewsample;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.aqoong.lib.objectflowview.FlowObject;
import com.aqoong.lib.objectflowview.FlowObjectManager;
import com.aqoong.lib.objectflowview.ObjectFlowView;

/**
 * [ObjectFlowViewSample]
 * <p>
 * Class: TestFragment
 * <p>
 * Created by aqoong on 2019-12-24.
 * - Email  : cooldnjsdn@gmail.com
 * - GitHub : https://github.com/aqoong
 * <p>
 * Description:
 */
public class TestFragment extends Fragment {
    private ObjectFlowView vObjectFlow;
    private boolean turnOn = true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        vObjectFlow = view.findViewById(R.id.objectflow);
        vObjectFlow.setTextSize(40);
        vObjectFlow.setFlowObjectManager(setData());

        view.findViewById(R.id.button_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(turnOn) {
                    vObjectFlow.setFlowObjectManager(setData2());
                    turnOn = false;
                }else{
                    vObjectFlow.setFlowObjectManager(setData());
                    turnOn = true;
                }
            }
        });
    }

    private FlowObjectManager setData(){
        FlowObject[] objects = new FlowObject[3];
        FlowObject tempObject = new FlowObject();
        tempObject.setType(FlowObject.ObjectType.TYPE_EMOTICON);
        tempObject.setImgSrc(R.drawable.ic_launcher_foreground);
        tempObject.setBackgroundColor("#000000");
        objects[0] = tempObject;
        for(int i = 1 ; i < 3 ; i++){
            FlowObject object = new FlowObject();
            object.setType(FlowObject.ObjectType.TYPE_TEXT);
            object.setStrText("TEST입니다. index : "+i);
            object.setTextColor("#FFFFFF");
            object.setBackgroundColor("#000000");
            objects[i] = object;
        }

        FlowObjectManager manager = new FlowObjectManager(getContext(), objects);
        return manager;
    }

    private FlowObjectManager setData2(){
        FlowObject[] objects = new FlowObject[5];
        FlowObject tempObject = new FlowObject();
        tempObject.setType(FlowObject.ObjectType.TYPE_EMOTICON);
        tempObject.setImgSrc(R.drawable.ic_launcher_foreground);
        tempObject.setBackgroundColor("#000000");
        objects[0] = tempObject;
        for(int i = 1 ; i < 5 ; i++){
            FlowObject object = new FlowObject();
            object.setType(FlowObject.ObjectType.TYPE_TEXT);
            object.setStrText("TEST2입니다. index : "+i);
            object.setTextColor("#FFFFFF");
            object.setBackgroundColor("#000000");
            objects[i] = object;
        }

        FlowObjectManager manager = new FlowObjectManager(getContext(), objects);
        return manager;
    }
}
